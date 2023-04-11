package com.twoguis.carfixer.model;

import java.sql.Date;
import java.util.ArrayList;
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
public class Agendamento {
    private int id;
    private Date data;
    private String objetivo;
    private Unidade unidade;
    private Status status;
    private List<Servico> servicos = new ArrayList<>();
    private Veiculo veiculo;
    private int id_veiculo;
}
