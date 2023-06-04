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

import com.twoguis.carfixer.model.Servico_Agenda;
import com.twoguis.carfixer.service.Servico_AgendaService;


@RestController
@RequestMapping("/api/v1/servico_agenda")
public class Servico_AgendaController {
    private final Servico_AgendaService servicoService;
    
    public Servico_AgendaController(Servico_AgendaService servicoService){
        this.servicoService = servicoService;
    }
    
    @GetMapping({"/", ""})
    public List<Servico_Agenda> consultarTodos(){
        List<Servico_Agenda> servicoList = servicoService.consultarTodos();
        return servicoList;
    }
    
    @GetMapping("/{id}")
    public Servico_Agenda consultarServico_Agenda(@PathVariable("id") int id){
        Servico_Agenda ret = servicoService.consultarPorId(id);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Servico_Agenda inserir(@RequestBody Servico_Agenda servico_agenda){
        Servico_Agenda ret = servicoService.inserir(servico_agenda);
        return ret;
    }
    
    @PutMapping({"", "/"})
    public Servico_Agenda alterar(@RequestBody Servico_Agenda servico_agenda){
        servicoService.alterar(servico_agenda);
        return servico_agenda;
    }
    
    @DeleteMapping("/{id}")
    public Servico_Agenda excluir(@PathVariable("id") int id){
        Servico_Agenda servico_agenda = servicoService.consultarPorId(id);
        if (servico_agenda == null){
            throw new RuntimeException("Nao existe tipo servico com este id para ser excluido....");
        }
        servicoService.excluir(id);
        return servico_agenda;
    }
}
