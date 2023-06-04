package com.twoguis.carfixer.model;



import java.sql.Timestamp;

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
public class Horario {  
    private int id_horario;
    private Status_Horario status;
    private Timestamp data;
}
