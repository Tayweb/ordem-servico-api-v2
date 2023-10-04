package com.web.ordemservico.controller.dto;

import com.web.ordemservico.shared.enums.TipoAcessoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoDTO {

    private Long idPessoa;
    private String nome;
    private String cpf;
    private String telefone;
    private boolean ativo;
    private TipoAcessoEnum tipoAcessoEnum;
}
