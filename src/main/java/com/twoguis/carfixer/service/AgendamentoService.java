package com.twoguis.carfixer.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.AgendamentoDao;
import com.twoguis.carfixer.model.Agendamento;

@Service
public class AgendamentoService {
    
    private final AgendamentoDao agendamentoDao;
    
    public AgendamentoService(Jdbi jdbi){
        this.agendamentoDao = jdbi.onDemand(AgendamentoDao.class);
    }
    
    public Agendamento inserir (Agendamento agendamento){
        int idAgendamento = agendamentoDao.insert(agendamento);
        agendamento.setId(idAgendamento);
        return agendamento;
    }
    
    public List<Agendamento> consultarTodos(){
        return agendamentoDao.getAll();
    }

    public Agendamento consultarPorId(int id){
        return agendamentoDao.get(id);
    }
    
    public void alterar(Agendamento agendamento){
        agendamentoDao.update(agendamento);
    }
    
    public void excluir(int id){
        agendamentoDao.delete(id);
    }
    
}
