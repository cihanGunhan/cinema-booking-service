package com.cihangunhan.cinemabookingservice.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "odemeler")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Odeme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "rezervasyon_id", nullable = false)
    private Rezervasyon rezervasyon;

    private BigDecimal tutar;
    private LocalDateTime odemeTarihi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OdemeDurumu odemeDurumu = OdemeDurumu.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OdemeYontemi odemeYontemi  = OdemeYontemi.KREDİ_KARTI;
}
