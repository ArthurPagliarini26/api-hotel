package com.estudo.api_hotel.dto.responses;

import lombok.Builder;

@Builder
public record ClienteResponse(Long id, String nome, String telefone, String cpf) {
}
