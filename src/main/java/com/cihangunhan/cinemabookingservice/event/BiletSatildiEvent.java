package com.cihangunhan.cinemabookingservice.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BiletSatildiEvent {
    private Long seansKoltukId;
    private Long rezervasyonId;
}