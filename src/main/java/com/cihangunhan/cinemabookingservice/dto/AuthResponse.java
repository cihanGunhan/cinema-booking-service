package com.cihangunhan.cinemabookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AuthResponse {
    private String token;
    private String email;
    private String fullName;
}