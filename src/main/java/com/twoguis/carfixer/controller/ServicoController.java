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

import com.twoguis.carfixer.model.Servico;
import com.twoguis.carfixer.service.ServicoService;


@RestController
@RequestMapping("/api/v1/servico")
public class ServicoController {
    private final ServicoService servicoService;
    
    public ServicoController(ServicoService servicoService){
        this.servicoService = servicoService;
    }
    
    @GetMapping({"/", ""})
    public List<Servico> consultarTodos(){
        List<Servico> servicoList = servicoService.consultarTodos();
        return servicoList;
    }
    
    @GetMapping("/{id}")
    public Servico consultarServico(@PathVariable("id") int id){
        Servico ret = servicoService.consultarPorId(id);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Servico inserir(@RequestBody Servico servico){
        Servico ret = servicoService.inserir(servico);
        return ret;
    }
    
    @PutMapping({"", "/"})
    public Servico alterar(@RequestBody Servico servico){
        servicoService.alterar(servico);
        return servico;
    }
    
    @DeleteMapping("/{id}")
    public Servico excluir(@PathVariable("id") int id){
        Servico servico = servicoService.consultarPorId(id);
        if (servico == null){
            throw new RuntimeException("Nao existe servico com este id para ser excluido....");
        }
        servicoService.excluir(id);
        return servico;
    }
}
