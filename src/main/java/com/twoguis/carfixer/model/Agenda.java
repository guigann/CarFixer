package com.twoguis.carfixer.model;

import java.sql.Date;
import java.util.List;

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
public class Agenda {
    private int id;
    private int id_horario;
    private int id_veiculo;
    private Status status;
    private Date dt_previsao;
    private Date dt_fim;
    private String observacao;
    private List<Produto> produtos;
}
