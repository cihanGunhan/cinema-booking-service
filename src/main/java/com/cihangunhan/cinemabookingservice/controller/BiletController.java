package com.cihangunhan.cinemabookingservice.controller;

import com.cihangunhan.cinemabookingservice.dto.BiletResponse;
import com.cihangunhan.cinemabookingservice.service.BiletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biletler")
@RequiredArgsConstructor
public class BiletController {
    private final BiletService biletService;

    @PostMapping("/{rezervasyonId}")
    public ResponseEntity<BiletResponse> createBilet(
            @PathVariable Long rezervasyonId ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(biletService.createBilet(rezervasyonId));
    }
    @GetMapping
    public ResponseEntity<List<BiletResponse>> getMyBiletler(){
        return ResponseEntity.ok(biletService.getMyBiletler());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BiletResponse> getBiletById(
            @PathVariable Long id){
        return ResponseEntity.ok(biletService.getBiletById(id));
    }
}
