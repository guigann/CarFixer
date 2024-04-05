package com.twoguis.carfixer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twoguis.carfixer.model.Horario;
import com.twoguis.carfixer.service.HorarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
// @RequestMapping("/api/v1/horario")
@RequestMapping(value = "/api/v1/horario", produces = {"application/json"})
@Tag(name = "horario")
@CrossOrigin("*")
public class HorarioController {
    private final HorarioService horarioService;
    
    public HorarioController(HorarioService horarioService){
        this.horarioService = horarioService;
    }
    
    @Operation(summary = "Consultar todos os horarios", method = "GET")
    @GetMapping({"/", ""})
    public List<Horario> get(){
        List<Horario> horarioList = horarioService.get();
        return horarioList;
    }
    
    @Operation(summary = "Consultar horario por id", method = "GET")
    @GetMapping("/{id}")
    public Horario consultarHorario(@PathVariable("id") int id){
        Horario ret = horarioService.getById(id);
        return ret;
    }
    
    @Operation(summary = "Inserir horario", method = "POST")
    @PostMapping({"", "/"})
    public Horario insert(@RequestBody Horario horario){
        Horario ret = horarioService.insert(horario);
        return ret;
    }
    
    @Operation(summary = "Atualizar horario", method = "PUT")
    @PutMapping({"", "/"})
    public Horario update(@RequestBody Horario horario){
        horarioService.update(horario);
        return horario;
    }
    
    @Operation(summary = "Deletar horario por id", method = "DELETE")
    @DeleteMapping("/{id}")
    public Horario delete(@PathVariable("id") int id){
        Horario horario = horarioService.getById(id);
        if (horario == null){
            throw new RuntimeException("Nao existe horario com este id para ser excluido....");
        }
        horarioService.delete(id);
        return horario;
    }
}
