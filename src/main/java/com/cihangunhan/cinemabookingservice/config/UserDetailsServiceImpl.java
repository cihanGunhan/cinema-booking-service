package com.cihangunhan.cinemabookingservice.config;

import com.cihangunhan.cinemabookingservice.repository.KullaniciRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final KullaniciRepository kullaniciRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return kullaniciRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Kullanıcı bulunamadı: " + email));
    }
}