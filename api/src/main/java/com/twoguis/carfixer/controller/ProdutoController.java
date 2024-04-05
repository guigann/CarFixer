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

import com.twoguis.carfixer.model.Produto;
import com.twoguis.carfixer.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
// @RequestMapping("/api/v1/produto")
@RequestMapping(value = "/api/v1/produto", produces = {"application/json"})
@Tag(name = "produto")
@CrossOrigin("*")
public class ProdutoController {
    private final ProdutoService produtoService;
    
    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }
    
    @Operation(summary = "Consultar todos os produtos", method = "GET")
    @GetMapping({"/", ""})
    public List<Produto> get(){
        List<Produto> produtoList = produtoService.get();
        return produtoList;
    }
    
    @Operation(summary = "Consultar produto por id", method = "GET")
    @GetMapping("/{id}")
    public Produto consultarProduto(@PathVariable("id") int id){
        Produto ret = produtoService.getById(id);
        return ret;
    }
    
    @Operation(summary = "Inserir produto", method = "POST")
    @PostMapping({"", "/"})
    public Produto insert(@RequestBody Produto produto){
        Produto ret = produtoService.insert(produto);
        return ret;
    }
    
    @Operation(summary = "Atualizar produto", method = "PUT")
    @PutMapping({"", "/"})
    public Produto update(@RequestBody Produto produto){
        produtoService.update(produto);
        return produto;
    }
    
    @Operation(summary = "Deletar produto por id", method = "DELETE")
    @DeleteMapping("/{id}")
    public Produto delete(@PathVariable("id") int id){
        Produto produto = produtoService.getById(id);
        if (produto == null){
            throw new RuntimeException("Nao existe produto com este id para ser excluido....");
        }
        produtoService.delete(id);
        return produto;
    }
}
