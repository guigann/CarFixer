package com.twoguis.carfixer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "senha")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    private Permission permission;
    private List<Veiculo> veiculos;
}
