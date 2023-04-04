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
public class Item_Servico {
    int id;
    String descricao;
    double valor;
    Servico servico;
}
