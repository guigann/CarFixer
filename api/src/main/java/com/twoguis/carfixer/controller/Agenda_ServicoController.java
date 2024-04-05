package com.twoguis.carfixer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twoguis.carfixer.model.Agenda_Servico;
import com.twoguis.carfixer.model.Servico;
import com.twoguis.carfixer.service.Agenda_ServicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
// @RequestMapping("/api/v1/agenda/{id_agenda}/servico")
@RequestMapping(value = "/api/v1/agenda/{id_agenda}/servico", produces = {"application/json"})
@Tag(name = "agenda_servico")
@CrossOrigin("*")
public class Agenda_ServicoController {
    private final Agenda_ServicoService agenda_servicoService;
    
    public Agenda_ServicoController(Agenda_ServicoService agenda_servicoService){
        this.agenda_servicoService = agenda_servicoService;
    }
    
    @Operation(summary = "Consultar todos os serviços por id da agenda", method = "GET")
    @GetMapping({"/", ""})
    public List<Servico> getByServicoAgenda(@PathVariable("id_agenda") int id_agenda){
        List<Servico> servicoList = agenda_servicoService.getByServicoAgenda(id_agenda);
        return servicoList;
    }
    
    @Operation(summary = "Consultar serviço por id da agenda e id do serviço", method = "GET")
    @GetMapping("/{id_servico}")
    public Servico get(@PathVariable("id_agenda") int id_agenda,
                            @PathVariable("id_servico") int id_servico){
        Servico servico = agenda_servicoService.get(id_agenda, id_servico);
        return servico;
    }
    
    @Operation(summary = "Inserir serviço na agenda", method = "POST")
    @PostMapping({"", "/"})
    public Servico insert(@RequestBody Agenda_Servico agenda_servico){
        Servico servico = agenda_servicoService.insert(agenda_servico);
        return servico;
    }
    
    @Operation(summary = "Excluir serviços da agenda", method = "DELETE")
    @DeleteMapping({"", "/"})
    public List<Servico> deleteByServicoAgenda(@PathVariable("id_agenda") int id_agenda){
        List<Servico> servicoList = agenda_servicoService.getByServicoAgenda(id_agenda);
        if (servicoList == null || servicoList.isEmpty()){
            throw new RuntimeException("Nao existe agenda com este id para ser excluido....");
        }
        agenda_servicoService.deleteByServicoAgenda(id_agenda);
        return servicoList;
    }
    
    @Operation(summary = "Excluir serviço da agenda por id", method = "DELETE")
    @DeleteMapping("/{id_servico}")
    public Servico delete(
            @PathVariable("id_agenda") int id_agenda, 
            @PathVariable("id_servico") int id_servico
    ){
        Servico servico = agenda_servicoService.get(id_agenda, id_servico);
        if (servico == null){
            throw new RuntimeException("Nao existe agenda com este id para ser excluido....");
        }
        agenda_servicoService.delete(id_agenda, id_servico);
        return servico;
    }
}
