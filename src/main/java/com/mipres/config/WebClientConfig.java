package com.mipres.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${spring.sispro.sispro-uri}")
    private String sisproUri;

    @Value("${spring.sispro.sispro-uri-fac}")
    private String facUri;


    @Bean(name = "sisproWebClient")
    public WebClient webClient() {
        int size = 16 * 1024 * 1024;

        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(size))
                .build();

        return WebClient.builder()
                .baseUrl(sisproUri)
                .exchangeStrategies(strategies)
                .build();
    }

    @Bean(name = "facWebClient")
    public WebClient facWebClient() {
        int size = 16 * 1024 * 1024;

        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(size))
                .build();

        return WebClient.builder()
                .baseUrl(facUri)
                .exchangeStrategies(strategies)
                .build();
    }
}