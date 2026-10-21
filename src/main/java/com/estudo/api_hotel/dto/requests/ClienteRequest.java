package com.estudo.api_hotel.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @NotBlank
        String cpf
) {
}
