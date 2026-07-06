package com.cihangunhan.cinemabookingservice.repository;
import com.cihangunhan.cinemabookingservice.entity.Odeme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface OdemeRepository extends JpaRepository <Odeme, Long> {
    Optional<Odeme> findByRezervasyonId(Long rezervasyonId);
    boolean existsByRezervasyonId(Long rezervasyonId);
}
