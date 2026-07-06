package com.cihangunhan.cinemabookingservice.dto;
import com.cihangunhan.cinemabookingservice.entity.RezervasyonDurumu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RezervasyonResponse {
    private Long id;
    private Long kullaniciId;
    private Long seansKoltukId;
    private LocalDateTime rezervasyonTarihi;
    private RezervasyonDurumu rezervasyonDurumu;


}
