package com.web.ordemservico.service.impl;

import com.web.ordemservico.controller.dto.ClienteDTO;
import com.web.ordemservico.domain.Cliente;
import com.web.ordemservico.repository.ClienteRepository;
import com.web.ordemservico.service.ClienteService;
import com.web.ordemservico.service.exception.DataIntegratyViolationException;
import com.web.ordemservico.service.exception.ObjetoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.web.ordemservico.shared.constants.ErrorConstants.CLIENTE_NAO_ENCONTRADO;
import static com.web.ordemservico.shared.constants.ErrorConstants.CPF_JA_CADASTRADO;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper mapper;

    @Override
    public ClienteDTO buscarClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()
                -> new ObjetoNaoEncontradoException(CLIENTE_NAO_ENCONTRADO));
        return mapper.map(cliente, ClienteDTO.class);
    }

    @Override
    public ClienteDTO buscarClienteByCpf(String cpf) {
        Cliente cliente = clienteRepository.findTecnicoByCpf(cpf).orElseThrow(()
                -> new ObjetoNaoEncontradoException(CLIENTE_NAO_ENCONTRADO));
        return mapper.map(cliente, ClienteDTO.class);
    }

    @Override
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        verificarCpfPossuiCadastro(clienteDTO);
        Cliente cliente = clienteRepository.save(mapper.map(clienteDTO, Cliente.class));
        return mapper.map(cliente, ClienteDTO.class);
    }

    @Override
    public List<ClienteDTO> listaCliente() {
        return clienteRepository.findAll().stream().map(cliente -> mapper.map(cliente, ClienteDTO.class)).toList();
    }

    private void verificarCpfPossuiCadastro(ClienteDTO clienteDTO) {
        ClienteDTO dto = buscarClienteByCpf(clienteDTO.getCpf());
        if (Objects.nonNull(dto) && !dto.getIdPessoa().equals(clienteDTO.getIdPessoa())) {
            throw new DataIntegratyViolationException(CPF_JA_CADASTRADO);
        }
    }
}
