package com.cihangunhan.cinemabookingservice.service;
import com.cihangunhan.cinemabookingservice.exception.DuplicateResourceException;
import com.cihangunhan.cinemabookingservice.config.CurrentUserProvider;
import com.cihangunhan.cinemabookingservice.dto.OdemeRequest;
import com.cihangunhan.cinemabookingservice.dto.OdemeResponse;
import com.cihangunhan.cinemabookingservice.entity.Odeme;
import com.cihangunhan.cinemabookingservice.entity.OdemeDurumu;
import com.cihangunhan.cinemabookingservice.entity.Rezervasyon;
import com.cihangunhan.cinemabookingservice.entity.RezervasyonDurumu;
import com.cihangunhan.cinemabookingservice.exception.ResourceNotFoundException;
import com.cihangunhan.cinemabookingservice.repository.OdemeRepository;
import com.cihangunhan.cinemabookingservice.repository.RezervasyonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OdemeService {

    private final OdemeRepository odemeRepository;
    private final RezervasyonRepository rezervasyonRepository;
    private final CurrentUserProvider currentUserProvider;

    public OdemeResponse createOdeme(OdemeRequest request) {
        Rezervasyon rezervasyon = rezervasyonRepository
                .findById(request.getRezervasyonId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Rezervasyon", request.getRezervasyonId()));

        if (odemeRepository.existsByRezervasyonId(request.getRezervasyonId())) {
            throw new DuplicateResourceException(
                    "Bu rezervasyon için zaten ödeme yapılmış");
        }

        Odeme odeme = Odeme.builder()
                .rezervasyon(rezervasyon)
                .tutar(request.getTutar())
                .odemeYontemi(request.getOdemeYontemi())
                .odemeTarihi(LocalDateTime.now())
                .odemeDurumu(OdemeDurumu.COMPLETED)
                .build();

        Odeme savedOdeme = odemeRepository.save(odeme);

        rezervasyon.setRezervasyonDurumu(RezervasyonDurumu.CONFIRMED);
        rezervasyonRepository.save(rezervasyon);

        return toResponse(savedOdeme);
    }

    public OdemeResponse getOdemeByRezervasyon(Long rezervasyonId) {
        Odeme odeme = odemeRepository.findByRezervasyonId(rezervasyonId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Odeme", rezervasyonId));
        return toResponse(odeme);
    }

    private OdemeResponse toResponse(Odeme odeme) {
        OdemeResponse response = new OdemeResponse();
        response.setId(odeme.getId());
        response.setRezervasyonId(odeme.getRezervasyon().getId());
        response.setTutar(odeme.getTutar());
        response.setOdemeTarihi(odeme.getOdemeTarihi());
        response.setOdemeDurumu(odeme.getOdemeDurumu());
        response.setOdemeYontemi(odeme.getOdemeYontemi());
        return response;
    }
}