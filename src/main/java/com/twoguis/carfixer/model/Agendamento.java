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
public class Agendamento {
    private int id_agendamento;
    private Date data;
    private int id_tipo_servico;
    private Status status;
    private int id_veiculo;
    private List<Servico> servicos;
}
