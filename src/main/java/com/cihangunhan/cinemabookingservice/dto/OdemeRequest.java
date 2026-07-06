package com.cihangunhan.cinemabookingservice.dto;
import com.cihangunhan.cinemabookingservice.entity.OdemeYontemi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OdemeRequest {
    private Long rezervasyonId;
    private BigDecimal tutar;
    private OdemeYontemi odemeYontemi;

}
