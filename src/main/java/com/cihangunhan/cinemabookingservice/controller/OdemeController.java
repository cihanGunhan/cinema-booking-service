package com.cihangunhan.cinemabookingservice.controller;

import com.cihangunhan.cinemabookingservice.dto.OdemeRequest;
import com.cihangunhan.cinemabookingservice.dto.OdemeResponse;
import com.cihangunhan.cinemabookingservice.service.OdemeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/odemeler")
@RequiredArgsConstructor
public class OdemeController {
    private final OdemeService odemeService;

    @PostMapping
    public ResponseEntity<OdemeResponse> createOdeme(
            @Valid @RequestBody OdemeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(odemeService.createOdeme(request));
    }
    @GetMapping("/{rezervasyonId}")
    public ResponseEntity<OdemeResponse> getOdemeByRezervasyon(
            @PathVariable Long rezervasyonId){
        return ResponseEntity.ok(odemeService.getOdemeByRezervasyon(rezervasyonId));
    }
}
