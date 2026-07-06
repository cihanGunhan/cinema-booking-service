package com.cihangunhan.cinemabookingservice.service;


import com.cihangunhan.cinemabookingservice.config.JwtService;
import com.cihangunhan.cinemabookingservice.dto.AuthResponse;
import com.cihangunhan.cinemabookingservice.entity.Kullanici;
import com.cihangunhan.cinemabookingservice.entity.Role;
import com.cihangunhan.cinemabookingservice.repository.KullaniciRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cihangunhan.cinemabookingservice.exception.DuplicateResourceException;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final KullaniciRepository kullaniciRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(String fullName, String email, String password) {
        if (kullaniciRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("Bu email zaten kayıtlı: " + email);
        }

        Kullanici user = Kullanici.builder()
                .fullName(fullName)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.USER)
                .build();

        kullaniciRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, email, fullName);
    }

    public AuthResponse login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        Kullanici kullanici = kullaniciRepository.findByEmail(email)
                .orElseThrow();

        String token = jwtService.generateToken(kullanici);
        return new AuthResponse(token, email, kullanici.getFullName());
    }




}