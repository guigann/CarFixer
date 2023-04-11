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
    private int id;
    private String placa;
    private String descricao;
    private boolean tipo;
    private List<Agendamento> agendamentos = new ArrayList<>();
    private Usuario usuario;
    private int id_usuario;
}
