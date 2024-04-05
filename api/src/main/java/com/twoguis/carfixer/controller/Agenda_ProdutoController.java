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

import com.twoguis.carfixer.model.Agenda_Produto;
import com.twoguis.carfixer.model.Produto;
import com.twoguis.carfixer.service.Agenda_ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
// @RequestMapping("/api/v1/agenda/{id_agenda}/produto")
@RequestMapping(value = "/api/v1/agenda/{id_agenda}/produto", produces = {"application/json"})
@Tag(name = "agenda_produto")
@CrossOrigin("*")
public class Agenda_ProdutoController {
    private final Agenda_ProdutoService agenda_produtoService;
    
    public Agenda_ProdutoController(Agenda_ProdutoService agenda_produtoService){
        this.agenda_produtoService = agenda_produtoService;
    }
    
    @Operation(summary = "Consultar todos os produtos por id da agenda", method = "GET")
    @GetMapping({"/", ""})
    public List<Produto> getByProdutoAgenda(@PathVariable("id_agenda") int id_agenda){
        List<Produto> produtoList = agenda_produtoService.getByProdutoAgenda(id_agenda);
        return produtoList;
    }
    
    @Operation(summary = "Consultar produto por id da agenda e id do produto", method = "GET")
    @GetMapping("/{id_produto}")
    public Produto get(@PathVariable("id_agenda") int id_agenda,
                            @PathVariable("id_produto") int id_produto){
        Produto produto = agenda_produtoService.get(id_agenda, id_produto);
        return produto;
    }
    
    @Operation(summary = "Inserir produto na agenda", method = "POST")
    @PostMapping({"", "/"})
    public Produto insert(@RequestBody Agenda_Produto agenda_produto){
        Produto produto = agenda_produtoService.insert(agenda_produto);
        return produto;
    }
    
    @Operation(summary = "Excluir produtos da agenda", method = "DELETE")
    @DeleteMapping({"", "/"})
    public List<Produto> deleteByProdutoAgenda(@PathVariable("id_agenda") int id_agenda){
        List<Produto> produtoList = agenda_produtoService.getByProdutoAgenda(id_agenda);
        if (produtoList == null || produtoList.isEmpty()){
            throw new RuntimeException("Nao existe agenda com este id para ser excluido....");
        }
        agenda_produtoService.deleteByProdutoAgenda(id_agenda);
        return produtoList;
    }
    
    @Operation(summary = "Excluir produto da agenda por id", method = "DELETE")
    @DeleteMapping("/{id_produto}")
    public Produto delete(
            @PathVariable("id_agenda") int id_agenda, 
            @PathVariable("id_produto") int id_produto
    ){
        Produto produto = agenda_produtoService.get(id_agenda, id_produto);
        if (produto == null){
            throw new RuntimeException("Nao existe agenda com este id para ser excluido....");
        }
        agenda_produtoService.delete(id_agenda, id_produto);
        return produto;
    }
}
