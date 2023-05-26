package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.Item_ServicoDao;
import com.twoguis.carfixer.dao.ServicoDao;
import com.twoguis.carfixer.model.Item_Servico;
import com.twoguis.carfixer.model.Servico;

@Service
public class ServicoService {
    
    private final ServicoDao servicoDao;
    private final Item_ServicoDao item_servicoDao;
    
    public ServicoService(Jdbi jdbi){
        this.servicoDao = jdbi.onDemand(ServicoDao.class);
        this.item_servicoDao = jdbi.onDemand(Item_ServicoDao.class);
    }
    
    public Servico inserir (Servico servico){
        int id_servico = servicoDao.insert(servico);
        servico.setId_servico(id_servico);
        return servico;
    }
    
    public List<Servico> consultarTodos(){
        List<Servico> servicos = servicoDao.getAll();

        for (Servico servico : servicos) {
            List<Item_Servico> item_servicos = item_servicoDao.getAllByServico(servico.getId_servico());
            servico.setItem_servicos(item_servicos);
        }

        return servicos;
    }

    public Servico consultarPorId(int id){
        return servicoDao.get(id);
    }
    
    public void alterar(Servico servico){
        servicoDao.update(servico);
    }
    
    public void excluir(int id){
        servicoDao.delete(id);
    }
    
}
