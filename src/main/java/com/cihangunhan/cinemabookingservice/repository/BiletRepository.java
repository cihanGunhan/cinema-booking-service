package com.cihangunhan.cinemabookingservice.repository;
import com.cihangunhan.cinemabookingservice.entity.Bilet;
import com.cihangunhan.cinemabookingservice.entity.BiletDurumu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiletRepository extends JpaRepository<Bilet, Long> {
    List<Bilet> findByRezervasyonId(Long rezervasyonId);
    List<Bilet> findByRezervasyonIdAndBiletDurumu(Long rezervasyonId, BiletDurumu durum);
    List<Bilet> findByRezervasyonKullaniciId(Long kullaniciId);
}
