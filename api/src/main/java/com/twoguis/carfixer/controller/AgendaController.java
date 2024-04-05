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

import com.twoguis.carfixer.model.Agenda;
import com.twoguis.carfixer.service.AgendaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
// @RequestMapping("/api/v1/agenda")
@RequestMapping(value = "/api/v1/agenda", produces = {"application/json"})
@Tag(name = "agenda")
@CrossOrigin("*")
public class AgendaController {
    private final AgendaService agendaService;
    
    public AgendaController(AgendaService agendaService){
        this.agendaService = agendaService;
    }
    
    @Operation(summary = "Consultar todos as agendas", method = "GET")
    @GetMapping({"/", ""})
    public List<Agenda> get(){
        List<Agenda> agendaList = agendaService.get();
        return agendaList;
    }
    
    @Operation(summary = "Consultar agenda por id", method = "GET")
    @GetMapping("/{id}")
    public Agenda consultarAgenda(@PathVariable("id") int id){
        Agenda ret = agendaService.getById(id);
        return ret;
    }

    @Operation(summary = "Consultar agenda por id do veiculo", method = "GET")
    @GetMapping("/veiculo/{id}")
    public List<Agenda> consultarPorVeiculo(@PathVariable("id") int id){
        List<Agenda> ret = agendaService.getByVeiculo(id);
        return ret;
    }

    @Operation(summary = "Inserir agenda", method = "POST")
    @PostMapping({"", "/"})
    public Agenda insert(@RequestBody Agenda agenda){
        Agenda ret = agendaService.insert(agenda);
        return ret;
    }
    
    @Operation(summary = "Atualizar agenda", method = "PUT")
    @PutMapping({"", "/"})
    public Agenda update(@RequestBody Agenda agenda){
        agendaService.update(agenda);
        return agenda;
    }
    
    @Operation(summary = "Deletar agenda por id", method = "DELETE")
    @DeleteMapping("/{id}")
    public Agenda delete(@PathVariable("id") int id){
        Agenda agenda = agendaService.getById(id);
        if (agenda == null){
            throw new RuntimeException("Nao existe agenda com este id para ser excluido....");
        }
        agendaService.delete(id);
        return agenda;
    }
}
