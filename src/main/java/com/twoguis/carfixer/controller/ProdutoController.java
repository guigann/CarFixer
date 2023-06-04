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
    public List<Produto> consultarTodos(){
        List<Produto> produtoList = produtoService.consultarTodos();
        return produtoList;
    }
    
    @GetMapping("/{id}")
    public Produto consultarProduto(@PathVariable("id") int id){
        Produto ret = produtoService.consultarPorId(id);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Produto inserir(@RequestBody Produto produto){
        Produto ret = produtoService.inserir(produto);
        return ret;
    }
    
    @PutMapping({"", "/"})
    public Produto alterar(@RequestBody Produto produto){
        produtoService.alterar(produto);
        return produto;
    }
    
    @DeleteMapping("/{id}")
    public Produto excluir(@PathVariable("id") int id){
        Produto produto = produtoService.consultarPorId(id);
        if (produto == null){
            throw new RuntimeException("Nao existe produto com este id para ser excluido....");
        }
        produtoService.excluir(id);
        return produto;
    }
}
