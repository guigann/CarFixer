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
public class Servico {
    int id;
    Date dataPrevEntrega;
    String observacao;
    List<Item_Servico> item_servicos = new ArrayList<>();
    Agendamento agendamento;
}
