package com.web.ordemservico.domain;

import com.web.ordemservico.shared.enums.TipoAcessoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tecnico")
public class Tecnico extends Pessoa {

    private boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private TipoAcessoEnum tipoAcessoEnum = TipoAcessoEnum.BASICO;

    public void promoverParaAdmin() {
        this.tipoAcessoEnum = TipoAcessoEnum.ADMIN;
    }

    public void rebaixarParaBasico() {
        this.tipoAcessoEnum = TipoAcessoEnum.BASICO;
    }
}
