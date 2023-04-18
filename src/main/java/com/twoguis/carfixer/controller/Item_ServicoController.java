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

import com.twoguis.carfixer.model.Item_Servico;
import com.twoguis.carfixer.service.Item_ServicoService;


@RestController
@RequestMapping("/api/v1/item_servico")
public class Item_ServicoController {
    private final Item_ServicoService item_servicoService;
    
    public Item_ServicoController(Item_ServicoService item_servicoService){
        this.item_servicoService = item_servicoService;
    }
    
    @GetMapping({"/", ""})
    public List<Item_Servico> consultarTodos(){
        List<Item_Servico> item_servicoList = item_servicoService.consultarTodos();
        return item_servicoList;
    }
    
    @GetMapping("/{id}")
    public Item_Servico consultarItem_Servico(@PathVariable("id") int id){
        Item_Servico ret = item_servicoService.consultarPorId(id);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Item_Servico inserir(@RequestBody Item_Servico item_servico){
        Item_Servico ret = item_servicoService.inserir(item_servico);
        return ret;
    }
    
    @PutMapping({"", "/"})
    public Item_Servico alterar(@RequestBody Item_Servico item_servico){
        item_servicoService.alterar(item_servico);
        return item_servico;
    }
    
    @DeleteMapping("/{id}")
    public Item_Servico excluir(@PathVariable("id") int id){
        Item_Servico item_servico = item_servicoService.consultarPorId(id);
        if (item_servico == null){
            throw new RuntimeException("Nao existe item_servico com este id para ser excluido....");
        }
        item_servicoService.excluir(id);
        return item_servico;
    }
}
