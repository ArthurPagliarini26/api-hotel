package com.estudo.api_hotel.service;

import com.estudo.api_hotel.dto.patchs.ClientePatch;
import com.estudo.api_hotel.dto.requests.ClienteRequest;
import com.estudo.api_hotel.dto.responses.ClienteResponse;
import com.estudo.api_hotel.dto.updates.ClienteUpdate;
import com.estudo.api_hotel.mapper.ClienteMapper;
import com.estudo.api_hotel.repositoy.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteService(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ClienteResponse cadastrar(ClienteRequest request) {
        return mapper.toResponse(repository.cadastrar(mapper.toEntity(request)));
    }

    public List<ClienteResponse> listar() {
        return repository.listar().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ClienteResponse buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .map(mapper::toResponse)
                .orElse(null);
    }

    public ClienteResponse atualizar(Long id, ClienteUpdate update) {
        return repository.buscarPorId(id)
                .map(cliente -> {
                    cliente.setNome(update.nome());
                    cliente.setTelefone(update.telefone());

                    return cliente;})
                .map(mapper::toResponse).orElse(null);
    }

    public ClienteResponse atualizarParcial(Long id, ClientePatch patch) {
        return repository.buscarPorId(id)
                .map(cliente -> {
                    if (patch.getNome() != null) {
                        cliente.setNome(patch.getNome());
                    }

                    if (patch.getTelefone() != null) {
                        cliente.setTelefone(patch.getTelefone());
                    }

                    return cliente;
                })
                .map(mapper::toResponse)
                .orElse(null);
    }
}
