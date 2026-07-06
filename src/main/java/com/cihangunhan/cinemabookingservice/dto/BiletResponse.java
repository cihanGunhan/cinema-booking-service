package com.cihangunhan.cinemabookingservice.dto;

import java.math.BigDecimal;
import com.cihangunhan.cinemabookingservice.entity.BiletDurumu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BiletResponse {
    private Long id;
    private Long rezervasyonId;
    private Long seansKoltukId;
    private BigDecimal fiyat;
    private BiletDurumu biletDurumu;


}
