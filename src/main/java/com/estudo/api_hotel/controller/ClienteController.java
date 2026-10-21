package com.estudo.api_hotel.controller;

import com.estudo.api_hotel.dto.patchs.ClientePatch;
import com.estudo.api_hotel.dto.requests.ClienteRequest;
import com.estudo.api_hotel.dto.responses.ClienteResponse;
import com.estudo.api_hotel.dto.updates.ClienteUpdate;
import com.estudo.api_hotel.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Clientes", description = "Gerenciamento de clientes do hotel")
@RestController
@RequestMapping("/api-hotel/v1/clientes")

public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @Operation(summary = "Cadastra cliente", description = "Recebe um json como request para cadastro do cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente criado"),
            @ApiResponse(responseCode = "405", description = "URI errada"),
            @ApiResponse(responseCode = "400", description = "Corpo da requisição inválido")
    })
    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody @Valid ClienteRequest request) {
        ClienteResponse response = service.cadastrar(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "Lista clientes", description = "Lista todos os clientes cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Clientes listados"),
            @ApiResponse(responseCode = "404", description = "URI inválida")
    })
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar() {
        return ResponseEntity.ok().body(service.listar());
    }

    @Operation(summary = "Busca cliente pelo id", description = "Recebe um id na uri e busca por um cliente com o mesmo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "URI inválida/Cliente não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(
            @Parameter(name = "Id do cliente")
            @PathVariable Long id
    ) {
        ClienteResponse response = service.buscarPorId(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Atualiza cliente pelo id", description = "Recebe um id na uri e busca por um cliente com o mesmo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "URI inválida/Cliente não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(
            @Parameter(name = "Id do cliente")
            @PathVariable Long id,
            @RequestBody @Valid ClienteUpdate update
    ) {
        ClienteResponse response = service.atualizar(id, update);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizarParcial(
            @Parameter(name = "Id do cliente")
            @PathVariable Long id,
            @RequestBody @Valid ClientePatch patch
    ) {
        ClienteResponse response = service.atualizarParcial(id, patch);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(response);
    }
}
