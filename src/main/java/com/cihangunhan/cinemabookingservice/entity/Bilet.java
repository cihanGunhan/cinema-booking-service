package com.cihangunhan.cinemabookingservice.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "biletler")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Bilet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rezervasyon_id", nullable = false)
    private Rezervasyon rezervasyon;

    @Column(nullable = false)
    private Long seansKoltukId;

    private BigDecimal fiyat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BiletDurumu biletDurumu = BiletDurumu.ACTIVE;
}
