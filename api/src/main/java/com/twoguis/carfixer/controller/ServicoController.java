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

import com.twoguis.carfixer.model.Servico;
import com.twoguis.carfixer.service.ServicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
// @RequestMapping("/api/v1/servico")
@RequestMapping(value = "/api/v1/servico", produces = {"application/json"})
@Tag(name = "servico")
@CrossOrigin("*")
public class ServicoController {
    private final ServicoService servicoService;
    
    public ServicoController(ServicoService servicoService){
        this.servicoService = servicoService;
    }
    
    @Operation(summary = "Consultar todos os serviços", method = "GET")
    @GetMapping({"/", ""})
    public List<Servico> get(){
        List<Servico> servicoList = servicoService.get();
        return servicoList;
    }
    
    @Operation(summary = "Consultar serviço por id", method = "GET")
    @GetMapping("/{id}")
    public Servico consultarServico(@PathVariable("id") int id){
        Servico ret = servicoService.getById(id);
        return ret;
    }
    
    @Operation(summary = "Inserir serviço", method = "POST")
    @PostMapping({"", "/"})
    public Servico insert(@RequestBody Servico servico){
        Servico ret = servicoService.insert(servico);
        return ret;
    }
    
    @Operation(summary = "Atualizar serviço", method = "PUT")
    @PutMapping({"", "/"})
    public Servico update(@RequestBody Servico servico){
        servicoService.update(servico);
        return servico;
    }
    
    @Operation(summary = "Deletar serviço por id", method = "DELETE")
    @DeleteMapping("/{id}")
    public Servico delete(@PathVariable("id") int id){
        Servico servico = servicoService.getById(id);
        if (servico == null){
            throw new RuntimeException("Nao existe servico com este id para ser excluido....");
        }
        servicoService.delete(id);
        return servico;
    }
}
