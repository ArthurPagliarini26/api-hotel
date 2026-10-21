package com.estudo.api_hotel.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Cliente {

    private Long id;
    private String nome;
    private String telefone;
    private String cpf;
}
