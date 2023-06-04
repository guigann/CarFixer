package com.twoguis.carfixer.controller;

import java.util.List;

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


@RestController
@RequestMapping("/api/v1/horario")
public class HorarioController {
    private final HorarioService horarioService;
    
    public HorarioController(HorarioService horarioService){
        this.horarioService = horarioService;
    }
    
    @GetMapping({"/", ""})
    public List<Horario> consultarTodos(){
        List<Horario> horarioList = horarioService.consultarTodos();
        return horarioList;
    }
    
    @GetMapping("/{id}")
    public Horario consultarHorario(@PathVariable("id") int id){
        Horario ret = horarioService.consultarPorId(id);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Horario inserir(@RequestBody Horario horario){
        Horario ret = horarioService.inserir(horario);
        return ret;
    }
    
    @PutMapping({"", "/"})
    public Horario alterar(@RequestBody Horario horario){
        horarioService.alterar(horario);
        return horario;
    }
    
    @DeleteMapping("/{id}")
    public Horario excluir(@PathVariable("id") int id){
        Horario horario = horarioService.consultarPorId(id);
        if (horario == null){
            throw new RuntimeException("Nao existe horario com este id para ser excluido....");
        }
        horarioService.excluir(id);
        return horario;
    }
}
