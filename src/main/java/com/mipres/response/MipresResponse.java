package com.mipres.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MipresResponse {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("IdTransaccion") 
    private Long idTransaccion; 
    
    // Opcional: Puedes agregar campos que siempre vienen en las respuestas de Mipres
    @JsonProperty("Mensaje")
    private String mensaje;
}