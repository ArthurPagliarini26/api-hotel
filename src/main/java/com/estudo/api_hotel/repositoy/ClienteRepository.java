package com.estudo.api_hotel.repositoy;

import com.estudo.api_hotel.dto.updates.ClienteUpdate;
import com.estudo.api_hotel.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ClienteRepository {

    private final List<Cliente> lista = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    public Cliente cadastrar(Cliente cliente) {
        cliente.setId(contadorId.getAndIncrement());
        lista.add(cliente);
        return cliente;
    }

    public List<Cliente> listar() {
        return lista;
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return lista.stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findFirst();
    }
}
