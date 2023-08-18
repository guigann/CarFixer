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

import com.twoguis.carfixer.model.Agenda;
import com.twoguis.carfixer.model.Servico_Agenda;
import com.twoguis.carfixer.service.Servico_AgendaService;


@RestController
@RequestMapping("/api/v1/servico/{id_servico}/agenda")
@CrossOrigin("*")
public class Servico_AgendaController {
    private final Servico_AgendaService servico_agendaService;
    
    public Servico_AgendaController(Servico_AgendaService servico_agendaService){
        this.servico_agendaService = servico_agendaService;
    }
    
    @GetMapping({"/", ""})
    public List<Agenda> getByServico(@PathVariable("id_servico") int id_servico){
        List<Agenda> agendaList = servico_agendaService.getByServico(id_servico);
        return agendaList;
    }
    
    @GetMapping("/{id_agenda}")
    public Agenda get(@PathVariable("id_servico") int id_servico,
                            @PathVariable("id_agenda") int id_agenda){
        Agenda agenda = servico_agendaService.get(id_servico, id_agenda);
        return agenda;
    }
    
    @PostMapping({"", "/"})
    public Agenda insert(@RequestBody Servico_Agenda servico_agenda){
        Agenda agenda = servico_agendaService.insert(servico_agenda);
        return agenda;
    }
    
    @DeleteMapping({"", "/"})
    public List<Agenda> deleteByServico(@PathVariable("id_servico") int id_servico){
        List<Agenda> agendaList = servico_agendaService.getByServico(id_servico);
        if (agendaList == null || agendaList.isEmpty()){
            throw new RuntimeException("Nao existe servico com este id para ser excluido....");
        }
        servico_agendaService.deleteByServico(id_servico);
        return agendaList;
    }
    
    @DeleteMapping("/{id_agenda}")
    public Agenda delete(
            @PathVariable("id_servico") int id_servico, 
            @PathVariable("id_agenda") int id_agenda
    ){
        Agenda agenda = servico_agendaService.get(id_servico, id_agenda);
        if (agenda == null){
            throw new RuntimeException("Nao existe servico com este id para ser excluido....");
        }
        servico_agendaService.delete(id_servico, id_agenda);
        return agenda;
    }
}
