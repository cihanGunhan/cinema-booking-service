package com.cihangunhan.cinemabookingservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RezervasyonRequest {
    @NotNull(message = "Koltuk ID boş olamaz")
    private Long seansKoltukId;
}
