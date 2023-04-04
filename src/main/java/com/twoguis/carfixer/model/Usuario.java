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
public class Usuario {
    int id;
    String nome;
    String cpf;
    String email;
    String telefone;
    String senha;
    boolean permission;
    List<Veiculo> veiculos = new ArrayList<>();
}
