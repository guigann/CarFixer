package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.ProdutoDao;
import com.twoguis.carfixer.model.Produto;

@Service
public class ProdutoService {
    
    private final ProdutoDao produtoDao;
    
    public ProdutoService(Jdbi jdbi){
        this.produtoDao = jdbi.onDemand(ProdutoDao.class);
    }
    
    public Produto inserir (Produto produto){
        int id_produto = produtoDao.insert(produto);
        produto.setId_produto(id_produto);
        return produto;
    }
    
    public List<Produto> consultarTodos(){
        return produtoDao.getAll();
    }

    public Produto consultarPorId(int id){
        return produtoDao.get(id);
    }
    
    public void alterar(Produto produto){
        produtoDao.update(produto);
    }
    
    public void excluir(int id){
        produtoDao.delete(id);
    }
    
}
