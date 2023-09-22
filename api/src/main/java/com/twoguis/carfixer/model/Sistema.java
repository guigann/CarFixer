package com.twoguis.carfixer.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sistema {
    private int id;
    private String nomeEmpresa;
    private Timestamp horario_rangeMin;
    private Timestamp horario_rangeMax;
    private int horasPorAgendamento;
}
