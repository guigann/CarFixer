package com.twoguis.carfixer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tipo_Servico {
    private int id_tipo_servico;
    private String nome;
    private String imagem;
    private String descricao;
}
