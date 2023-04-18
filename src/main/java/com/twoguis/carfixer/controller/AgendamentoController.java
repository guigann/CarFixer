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

import com.twoguis.carfixer.model.Agendamento;
import com.twoguis.carfixer.service.AgendamentoService;


@RestController
@RequestMapping("/api/v1/agendamento")
public class AgendamentoController {
    private final AgendamentoService agendamentoService;
    
    public AgendamentoController(AgendamentoService agendamentoService){
        this.agendamentoService = agendamentoService;
    }
    
    @GetMapping({"/", ""})
    public List<Agendamento> consultarTodos(){
        List<Agendamento> agendamentoList = agendamentoService.consultarTodos();
        return agendamentoList;
    }
    
    @GetMapping("/{id}")
    public Agendamento consultarAgendamento(@PathVariable("id") int id){
        Agendamento ret = agendamentoService.consultarPorId(id);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Agendamento inserir(@RequestBody Agendamento agendamento){
        Agendamento ret = agendamentoService.inserir(agendamento);
        return ret;
    }
    
    @PutMapping({"", "/"})
    public Agendamento alterar(@RequestBody Agendamento agendamento){
        agendamentoService.alterar(agendamento);
        return agendamento;
    }
    
    @DeleteMapping("/{id}")
    public Agendamento excluir(@PathVariable("id") int id){
        Agendamento agendamento = agendamentoService.consultarPorId(id);
        if (agendamento == null){
            throw new RuntimeException("Nao existe agendamento com este id para ser excluido....");
        }
        agendamentoService.excluir(id);
        return agendamento;
    }
}
