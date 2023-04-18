package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.ServicoDao;
import com.twoguis.carfixer.model.Servico;

@Service
public class ServicoService {
    
    private final ServicoDao servicoDao;
    
    public ServicoService(Jdbi jdbi){
        this.servicoDao = jdbi.onDemand(ServicoDao.class);
    }
    
    public Servico inserir (Servico servico){
        int idServico = servicoDao.insert(servico);
        servico.setId(idServico);
        return servico;
    }
    
    public List<Servico> consultarTodos(){
        return servicoDao.getAll();
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
