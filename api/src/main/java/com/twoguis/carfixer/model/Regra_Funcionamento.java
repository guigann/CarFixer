package com.twoguis.carfixer.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Regra_Funcionamento {
    private int id;
    private String dias;
    private String hr_inicio;
    private String hr_termino;
    private List<Integer> diasInt;
}
