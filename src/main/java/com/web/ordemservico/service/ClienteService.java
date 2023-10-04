package com.web.ordemservico.service;

import com.web.ordemservico.controller.dto.ClienteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    ClienteDTO buscarClienteById(Long id);
    ClienteDTO buscarClienteByCpf(String cpf);
    ClienteDTO createCliente(ClienteDTO clienteDTO);
    List<ClienteDTO> listaCliente();
}
