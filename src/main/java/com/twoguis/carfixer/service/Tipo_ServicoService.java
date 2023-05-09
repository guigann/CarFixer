package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.Tipo_ServicoDao;
import com.twoguis.carfixer.model.Tipo_Servico;

@Service
public class Tipo_ServicoService {
    
    private final Tipo_ServicoDao servicoDao;
    
    public Tipo_ServicoService(Jdbi jdbi){
        this.servicoDao = jdbi.onDemand(Tipo_ServicoDao.class);
    }
    
    public Tipo_Servico inserir (Tipo_Servico tipo_servico){
        int id_tipo_Servico = servicoDao.insert(tipo_servico);
        tipo_servico.setId_tipo_servico(id_tipo_Servico);
        return tipo_servico;
    }
    
    public List<Tipo_Servico> consultarTodos(){
        return servicoDao.getAll();
    }

    public Tipo_Servico consultarPorId(int id){
        return servicoDao.get(id);
    }
    
    public void alterar(Tipo_Servico tipo_servico){
        servicoDao.update(tipo_servico);
    }
    
    public void excluir(int id){
        servicoDao.delete(id);
    }
    
}
