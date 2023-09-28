package com.web.ordemservico.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TipoAcessoEnum {
    BASICO("Básico"),
    ADMIN("Admin");

    private String descricao;
}
