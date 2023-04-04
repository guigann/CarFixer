package com.twoguis.carfixer.model;

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
public class Veiculo {
    int id;
    String placa;
    String descricao;
    boolean tipo;    
    List<Agendamento> agendamentos = new ArrayList<>();
    Usuario usuario;
}
