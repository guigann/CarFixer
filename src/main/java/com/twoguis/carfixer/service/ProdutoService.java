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
    
    public Produto insert (Produto produto){
        int id_produto = produtoDao.insert(produto);
        produto.setId_produto(id_produto);
        return produto;
    }
    
    public List<Produto> get(){
        return produtoDao.get();
    }

    public Produto getById(int id){
        return produtoDao.getById(id);
    }
    
    public void update(Produto produto){
        produtoDao.update(produto);
    }
    
    public void delete(int id){
        produtoDao.delete(id);
    }
    
}
