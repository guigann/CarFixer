package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.Item_ServicoDao;
import com.twoguis.carfixer.model.Item_Servico;

@Service
public class Item_ServicoService {
    
    private final Item_ServicoDao item_servicoDao;
    
    public Item_ServicoService(Jdbi jdbi){
        this.item_servicoDao = jdbi.onDemand(Item_ServicoDao.class);
    }
    
    public Item_Servico inserir (Item_Servico item_servico){
        int id_item_Servico = item_servicoDao.insert(item_servico);
        item_servico.setId_item_servico(id_item_Servico);
        return item_servico;
    }
    
    public List<Item_Servico> consultarTodos(){
        return item_servicoDao.getAll();
    }

    public Item_Servico consultarPorId(int id){
        return item_servicoDao.get(id);
    }
    
    public void alterar(Item_Servico item_servico){
        item_servicoDao.update(item_servico);
    }
    
    public void excluir(int id){
        item_servicoDao.delete(id);
    }
    
}
