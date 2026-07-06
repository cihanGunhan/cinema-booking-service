package com.cihangunhan.cinemabookingservice.dto;
import com.cihangunhan.cinemabookingservice.entity.OdemeDurumu;
import com.cihangunhan.cinemabookingservice.entity.OdemeYontemi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OdemeResponse {
    private Long id;
    private Long rezervasyonId;
    private BigDecimal tutar;
    private LocalDateTime odemeTarihi;
    private OdemeDurumu odemeDurumu;
    private OdemeYontemi odemeYontemi;

}
