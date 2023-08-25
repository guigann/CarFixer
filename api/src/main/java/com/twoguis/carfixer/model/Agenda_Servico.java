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
public class Agenda_Servico extends Servico {
    private int id_agenda;
    private String observacao;
}
