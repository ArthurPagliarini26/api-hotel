package com.estudo.api_hotel.mapper;

import com.estudo.api_hotel.dto.requests.ClienteRequest;
import com.estudo.api_hotel.dto.responses.ClienteResponse;
import com.estudo.api_hotel.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequest request) {
        return Cliente.builder()
                .nome(request.nome())
                .telefone(request.telefone())
                .cpf(request.cpf())
                .build();
    }

    public ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .telefone(cliente.getTelefone())
                .cpf(cliente.getCpf())
                .build();
    }
}
