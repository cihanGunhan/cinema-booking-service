package com.cihangunhan.cinemabookingservice.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rezervasyonlar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Rezervasyon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Kullanici kullanici;

    @Column(nullable = false)
    private Long seansKoltukId;

    private LocalDateTime rezervasyonTarihi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RezervasyonDurumu rezervasyonDurumu = RezervasyonDurumu.PENDING;
}
