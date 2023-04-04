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
    int id;
    Date data;
    String objetivo;
    Unidade unidade;
    Status status;
    List<Servico> servicos = new ArrayList<>();
    Veiculo veiculo;
}
