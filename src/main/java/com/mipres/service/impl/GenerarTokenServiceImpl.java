package com.mipres.service.impl;

import com.mipres.service.GenerarTokenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
public class GenerarTokenServiceImpl implements GenerarTokenService{

    @Value("${spring.sispro.sispro-nit}")
    private String nit;

    @Value("${spring.sispro.sispro-token}")
    private String token;
    
    private final WebClient webClient;

    public GenerarTokenServiceImpl(@Qualifier("sisproWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String getToken() {
        return Objects.requireNonNull(webClient.get()
                .uri("/GenerarToken/{nit}/{token}", nit, token)
                .retrieve()
                .bodyToMono(String.class)
                .block()).replaceAll("\"", "").trim();
    }
}
