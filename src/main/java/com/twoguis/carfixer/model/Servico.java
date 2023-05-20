package com.twoguis.carfixer.model;

import java.sql.Date;
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
public class Servico {
    private int id_servico;
    private Date dataPrevEntrega;
    private String observacao;
    private int id_agendamento;
    private int mecanico;
}
