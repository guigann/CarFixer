package com.twoguis.carfixer.model;

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
    private int id_veiculo;
    private String placa;
    private String modelo;
    private Tipo tipo;
    private int id_usuario;
    private List<Agendamento> agendamentos;
}
