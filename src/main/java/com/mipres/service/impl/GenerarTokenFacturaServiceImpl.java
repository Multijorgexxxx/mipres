package com.mipres.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mipres.service.GenerarTokenFacturaService;

@Service
public class GenerarTokenFacturaServiceImpl implements GenerarTokenFacturaService{

    @Value("${spring.sispro.sispro-nit}")
    private String nit;

    @Value("${spring.sispro.sispro-token-fac}")
    private String token;
    
    private final WebClient webClient;

    public GenerarTokenFacturaServiceImpl(@Qualifier("facWebClient") WebClient webClient) {
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
