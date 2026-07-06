package com.cihangunhan.cinemabookingservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
    @NotBlank(message = "Ad soyad boş olamaz")
    private String fullName;

    @Email(message = "Geçerli bir email girin")
    @NotBlank(message = "Email boş olamaz")
    private String email;

    @Size(min = 6, message = "Şifre en az 6 karakter olmalı")
    @NotBlank(message = "Şifre boş olamaz")
    private String password;

}
