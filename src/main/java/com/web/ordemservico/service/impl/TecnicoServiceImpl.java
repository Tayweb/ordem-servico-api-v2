package com.web.ordemservico.service.impl;

import com.web.ordemservico.controller.dto.TecnicoDTO;
import com.web.ordemservico.domain.Tecnico;
import com.web.ordemservico.repository.TecnicoRepository;
import com.web.ordemservico.service.TecnicoService;
import com.web.ordemservico.service.exception.DataIntegratyViolationException;
import com.web.ordemservico.service.exception.ObjetoNaoEncontradoException;
import com.web.ordemservico.shared.enums.TipoAcessoEnum;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.web.ordemservico.shared.constants.ErrorConstants.CPF_JA_CADASTRADO;
import static com.web.ordemservico.shared.constants.ErrorConstants.TECNICO_NAO_ENCONTRADO;

@Service
@AllArgsConstructor
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final ModelMapper mapper;

    @Override
    public TecnicoDTO buscarTecnicoById(Long id) {
        Tecnico tecnico = tecnicoRepository.findById(id).orElseThrow(()
                -> new ObjetoNaoEncontradoException(TECNICO_NAO_ENCONTRADO));
        return mapper.map(tecnico, TecnicoDTO.class);
    }

    @Override
    public TecnicoDTO buscarTecnicoByCpf(String cpf) {
        Tecnico tecnico = tecnicoRepository.findTecnicoByCpf(cpf).orElseThrow(()
                -> new ObjetoNaoEncontradoException(TECNICO_NAO_ENCONTRADO));
        return mapper.map(tecnico, TecnicoDTO.class);
    }

    @Override
    public TecnicoDTO createTecnico(TecnicoDTO tecnicoDTO) {
        verificarCpfPossuiCadastro(tecnicoDTO);
        Tecnico tecnico = tecnicoRepository.save(mapper.map(tecnicoDTO, Tecnico.class));
        return mapper.map(tecnico, TecnicoDTO.class);
    }

    @Override
    public List<TecnicoDTO> listaTecnico() {
        return tecnicoRepository.findAll().stream().map(tecnico -> mapper.map(tecnico, TecnicoDTO.class)).toList();
    }

    @Override
    public void promoverParaAdmin(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setTipoAcessoEnum(TipoAcessoEnum.ADMIN);
        tecnicoRepository.save(mapper.map(tecnicoDTO, Tecnico.class));

    }

    @Override
    public void rebaixarParaBasico(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setTipoAcessoEnum(TipoAcessoEnum.BASICO);
        tecnicoRepository.save(mapper.map(tecnicoDTO, Tecnico.class));
    }

    @Override
    public void desativarTecnico(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setAtivo(false);
        tecnicoRepository.save(mapper.map(tecnicoDTO, Tecnico.class));
    }

    private void verificarCpfPossuiCadastro(TecnicoDTO tecnicoDTO) {
       TecnicoDTO dto = buscarTecnicoByCpf(tecnicoDTO.getCpf());
        if (Objects.nonNull(dto) && !dto.getIdPessoa().equals(tecnicoDTO.getIdPessoa())) {
            throw new DataIntegratyViolationException(CPF_JA_CADASTRADO);
        }
    }
}
