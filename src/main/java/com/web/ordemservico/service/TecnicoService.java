package com.web.ordemservico.service;

import com.web.ordemservico.controller.dto.TecnicoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TecnicoService {

    TecnicoDTO buscarTecnicoById(Long id);
    TecnicoDTO buscarTecnicoByCpf(String cpf);
    TecnicoDTO createTecnico(TecnicoDTO tecnicoDTO);
    List<TecnicoDTO> listaTecnico();
    void promoverParaAdmin(TecnicoDTO tecnicoDTO);
    void rebaixarParaBasico(TecnicoDTO tecnicoDTO);
    void desativarTecnico(TecnicoDTO tecnicoDTO);
}

