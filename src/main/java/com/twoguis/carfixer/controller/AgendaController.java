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

import com.twoguis.carfixer.model.Agenda;
import com.twoguis.carfixer.service.AgendaService;


@RestController
@RequestMapping("/api/v1/agenda")
public class AgendaController {
    private final AgendaService agendaService;
    
    public AgendaController(AgendaService agendaService){
        this.agendaService = agendaService;
    }
    
    @GetMapping({"/", ""})
    public List<Agenda> consultarTodos(){
        List<Agenda> agendaList = agendaService.consultarTodos();
        return agendaList;
    }
    
    @GetMapping("/{id}")
    public Agenda consultarAgenda(@PathVariable("id") int id){
        Agenda ret = agendaService.consultarPorId(id);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Agenda inserir(@RequestBody Agenda agenda){
        Agenda ret = agendaService.inserir(agenda);
        return ret;
    }
    
    @PutMapping({"", "/"})
    public Agenda alterar(@RequestBody Agenda agenda){
        agendaService.alterar(agenda);
        return agenda;
    }
    
    @DeleteMapping("/{id}")
    public Agenda excluir(@PathVariable("id") int id){
        Agenda agenda = agendaService.consultarPorId(id);
        if (agenda == null){
            throw new RuntimeException("Nao existe agenda com este id para ser excluido....");
        }
        agendaService.excluir(id);
        return agenda;
    }
}
