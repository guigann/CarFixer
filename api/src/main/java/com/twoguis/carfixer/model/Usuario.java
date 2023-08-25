package com.twoguis.carfixer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    @JsonIgnore
    private String senha;
    private Permission permission;
    private List<Veiculo> veiculos;
}
