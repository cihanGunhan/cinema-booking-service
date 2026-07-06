package com.cihangunhan.cinemabookingservice.controller;

import com.cihangunhan.cinemabookingservice.dto.RezervasyonRequest;
import com.cihangunhan.cinemabookingservice.dto.RezervasyonResponse;
import com.cihangunhan.cinemabookingservice.service.RezervasyonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rezervasyonlar")
@RequiredArgsConstructor
public class RezervasyonController {
    private final RezervasyonService rezervasyonService;

    @PostMapping
    public ResponseEntity<RezervasyonResponse>createRezervasyon(
            @Valid @RequestBody RezervasyonRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(rezervasyonService.createRezervasyon(request));
    }

    @GetMapping
    public ResponseEntity<List<RezervasyonResponse>> getMyRezervasyon(){
        return ResponseEntity.ok(rezervasyonService.getMyRezervasyon());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RezervasyonResponse> getRezervasyonById(
            @PathVariable Long id){
        return ResponseEntity.ok(rezervasyonService.getRezervasyonById(id));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void>cancelRezervasyon(@PathVariable Long id){
        rezervasyonService.cancelRezervasyon(id);
        return ResponseEntity.noContent().build();

    }
}
