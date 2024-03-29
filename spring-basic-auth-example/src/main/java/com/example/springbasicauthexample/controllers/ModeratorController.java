package com.example.springbasicauthexample.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/moderator")
public class ModeratorController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<String> moderatorGet(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(
                MessageFormat.format("Осуществлен вызов метода moderatorGet: {0}. Роль пользователя: {1}",
                        userDetails.getUsername(),
                        userDetails.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.joining(",")))
        );
    }
}