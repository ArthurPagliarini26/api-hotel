package com.estudo.api_hotel.dto.updates;

import jakarta.validation.constraints.NotBlank;

public record ClienteUpdate(
        @NotBlank
        String nome,

        @NotBlank
        String telefone) {
}
