package com.cihangunhan.cinemabookingservice.service;


import com.cihangunhan.cinemabookingservice.config.CurrentUserProvider;
import com.cihangunhan.cinemabookingservice.dto.RezervasyonRequest;
import com.cihangunhan.cinemabookingservice.dto.RezervasyonResponse;
import com.cihangunhan.cinemabookingservice.entity.Kullanici;
import com.cihangunhan.cinemabookingservice.entity.Rezervasyon;
import com.cihangunhan.cinemabookingservice.entity.RezervasyonDurumu;
import com.cihangunhan.cinemabookingservice.exception.ResourceNotFoundException;
import com.cihangunhan.cinemabookingservice.exception.UnauthorizedException;
import com.cihangunhan.cinemabookingservice.repository.RezervasyonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RezervasyonService {
    private final RezervasyonRepository rezervasyonRepository;
    private final CurrentUserProvider currentUserProvider;

    public RezervasyonResponse createRezervasyon(RezervasyonRequest request) {
        Kullanici currentUser = currentUserProvider.getCurrentUser();
        Rezervasyon rezervasyon = Rezervasyon.builder()
                .kullanici(currentUser)
                .seansKoltukId(request.getSeansKoltukId())
                .rezervasyonTarihi(LocalDateTime.now())
                .rezervasyonDurumu(RezervasyonDurumu.PENDING)
                .build();
        return toResponse(rezervasyonRepository.save(rezervasyon));

    }
    public List<RezervasyonResponse> getMyRezervasyon() {
        Long userId = currentUserProvider.getCurrentUserId();
        return rezervasyonRepository.findByKullaniciId(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    public RezervasyonResponse getRezervasyonById(Long id) {
        Rezervasyon rezervasyon = findAndValidateOwnership(id);
        return toResponse(rezervasyon);
    }

    public void cancelRezervasyon(Long id) {
        Rezervasyon rezervasyon = findAndValidateOwnership(id);
        rezervasyon.setRezervasyonDurumu(RezervasyonDurumu.CANCELLED);
        rezervasyonRepository.save(rezervasyon);
    }


    private Rezervasyon findAndValidateOwnership(Long id) {
        Rezervasyon rezervasyon = rezervasyonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rezervasyon", id));

        Long currentUserId = currentUserProvider.getCurrentUserId();
        if (!rezervasyon.getKullanici().getId().equals(currentUserId)) {
            throw new UnauthorizedException("Bu rezervasyona erişim yetkiniz yok");
        }

        return rezervasyon;
    }
    private RezervasyonResponse toResponse(Rezervasyon rezervasyon) {
        RezervasyonResponse response = new RezervasyonResponse();
        response.setId(rezervasyon.getId());
        response.setKullaniciId(rezervasyon.getKullanici().getId());
        response.setSeansKoltukId(rezervasyon.getSeansKoltukId());
        response.setRezervasyonTarihi(rezervasyon.getRezervasyonTarihi());
        response.setRezervasyonDurumu(rezervasyon.getRezervasyonDurumu());
        return response;
    }


}
