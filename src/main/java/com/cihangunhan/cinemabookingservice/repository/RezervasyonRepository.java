package com.cihangunhan.cinemabookingservice.repository;
import com.cihangunhan.cinemabookingservice.entity.Rezervasyon;
import com.cihangunhan.cinemabookingservice.entity.RezervasyonDurumu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface RezervasyonRepository extends JpaRepository<Rezervasyon, Long> {
    List<Rezervasyon> findByKullaniciId(Long kullaniciId);
    List<Rezervasyon> findByKullaniciIdAndRezervasyonDurumu(Long kullaniciId, RezervasyonDurumu durum);
    Optional<Rezervasyon> findByIdAndKullaniciId(Long id, Long kullaniciId);


}
