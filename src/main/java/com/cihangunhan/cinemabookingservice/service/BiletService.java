package com.cihangunhan.cinemabookingservice.service;
import com.cihangunhan.cinemabookingservice.event.BiletSatildiEvent;
import com.cihangunhan.cinemabookingservice.config.CurrentUserProvider;
import com.cihangunhan.cinemabookingservice.dto.BiletResponse;
import com.cihangunhan.cinemabookingservice.entity.Bilet;
import com.cihangunhan.cinemabookingservice.entity.BiletDurumu;
import com.cihangunhan.cinemabookingservice.entity.Rezervasyon;
import com.cihangunhan.cinemabookingservice.exception.ResourceNotFoundException;
import com.cihangunhan.cinemabookingservice.exception.UnauthorizedException;
import com.cihangunhan.cinemabookingservice.repository.BiletRepository;
import com.cihangunhan.cinemabookingservice.repository.RezervasyonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BiletService {

    private final BiletRepository biletRepository;
    private final RezervasyonRepository rezervasyonRepository;
    private final CurrentUserProvider currentUserProvider;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public BiletResponse createBilet(Long rezervasyonId) {
        Rezervasyon rezervasyon = rezervasyonRepository.findById(rezervasyonId)
                .orElseThrow(() -> new ResourceNotFoundException("Rezervasyon", rezervasyonId));

        Bilet bilet = Bilet.builder()
                .rezervasyon(rezervasyon)
                .seansKoltukId(rezervasyon.getSeansKoltukId())
                .biletDurumu(BiletDurumu.ACTIVE)
                .build();

        Bilet savedBilet = biletRepository.save(bilet);

        // Kafka'ya event gönder
        BiletSatildiEvent event = new BiletSatildiEvent(
                rezervasyon.getSeansKoltukId(),
                rezervasyonId
        );
        kafkaTemplate.send("bilet-satildi", event);

        return toResponse(savedBilet);
    }

    public List<BiletResponse> getMyBiletler() {
        Long userId = currentUserProvider.getCurrentUserId();
        return biletRepository.findByRezervasyonKullaniciId(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BiletResponse getBiletById(Long id) {
        Bilet bilet = biletRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bilet", id));

        Long currentUserId = currentUserProvider.getCurrentUserId();
        if (!bilet.getRezervasyon().getKullanici().getId().equals(currentUserId)) {
            throw new UnauthorizedException("Bu bilete erişim yetkiniz yok");
        }

        return toResponse(bilet);
    }

    private BiletResponse toResponse(Bilet bilet) {
        BiletResponse response = new BiletResponse();
        response.setId(bilet.getId());
        response.setRezervasyonId(bilet.getRezervasyon().getId());
        response.setSeansKoltukId(bilet.getSeansKoltukId());
        response.setFiyat(bilet.getFiyat());
        response.setBiletDurumu(bilet.getBiletDurumu());
        return response;
    }
}