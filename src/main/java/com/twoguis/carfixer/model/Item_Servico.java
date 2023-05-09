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
    private int id_item_servico;
    private String descricao;
    private double valor;
    private int id_servico;
}
