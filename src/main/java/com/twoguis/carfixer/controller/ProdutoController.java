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

import com.twoguis.carfixer.model.Produto;
import com.twoguis.carfixer.service.ProdutoService;


@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {
    private final ProdutoService produtoService;
    
    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }
    
    @GetMapping({"/", ""})
    public List<Produto> get(){
        List<Produto> produtoList = produtoService.get();
        return produtoList;
    }
    
    @GetMapping("/{id}")
    public Produto consultarProduto(@PathVariable("id") int id){
        Produto ret = produtoService.getById(id);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Produto insert(@RequestBody Produto produto){
        Produto ret = produtoService.insert(produto);
        return ret;
    }
    
    @PutMapping({"", "/"})
    public Produto update(@RequestBody Produto produto){
        produtoService.update(produto);
        return produto;
    }
    
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
