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

import com.twoguis.carfixer.model.Tipo_Servico;
import com.twoguis.carfixer.service.Tipo_ServicoService;


@RestController
@RequestMapping("/api/v1/tipo_servico")
public class Tipo_ServicoController {
    private final Tipo_ServicoService servicoService;
    
    public Tipo_ServicoController(Tipo_ServicoService servicoService){
        this.servicoService = servicoService;
    }
    
    @GetMapping({"/", ""})
    public List<Tipo_Servico> consultarTodos(){
        List<Tipo_Servico> servicoList = servicoService.consultarTodos();
        return servicoList;
    }
    
    @GetMapping("/{id}")
    public Tipo_Servico consultarTipo_Servico(@PathVariable("id") int id){
        Tipo_Servico ret = servicoService.consultarPorId(id);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Tipo_Servico inserir(@RequestBody Tipo_Servico tipo_servico){
        Tipo_Servico ret = servicoService.inserir(tipo_servico);
        return ret;
    }
    
    @PutMapping({"", "/"})
    public Tipo_Servico alterar(@RequestBody Tipo_Servico tipo_servico){
        servicoService.alterar(tipo_servico);
        return tipo_servico;
    }
    
    @DeleteMapping("/{id}")
    public Tipo_Servico excluir(@PathVariable("id") int id){
        Tipo_Servico tipo_servico = servicoService.consultarPorId(id);
        if (tipo_servico == null){
            throw new RuntimeException("Nao existe tipo servico com este id para ser excluido....");
        }
        servicoService.excluir(id);
        return tipo_servico;
    }
}
