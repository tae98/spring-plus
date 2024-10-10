package org.example.expert.domain.health;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.auth.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthController {
    private final AuthService authService;

    @GetMapping("/health/check")
    public String healthCheck() {
        return "Health Check Success";
    }
}