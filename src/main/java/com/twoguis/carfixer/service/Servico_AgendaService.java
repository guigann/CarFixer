package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.Servico_AgendaDao;
import com.twoguis.carfixer.model.Servico_Agenda;

@Service
public class Servico_AgendaService {
    
    private final Servico_AgendaDao servico_agendaDao;
    
    public Servico_AgendaService(Jdbi jdbi){
        this.servico_agendaDao = jdbi.onDemand(Servico_AgendaDao.class);
    }
    
    public Servico_Agenda inserir (Servico_Agenda servico_agenda){
        // int id_servico_agenda = servico_agendaDao.insert(servico_agenda);
        // servico_agenda.setId_servico_agenda(id_servico_agenda);
        servico_agendaDao.insert(servico_agenda);
        return servico_agenda;
    }
    
    public List<Servico_Agenda> consultarTodos(){
        return servico_agendaDao.getAll();
    }

    public Servico_Agenda consultarPorId(int id){
        return servico_agendaDao.get(id);
    }
    
    public void alterar(Servico_Agenda servico_agenda){
        servico_agendaDao.update(servico_agenda);
    }
    
    public void excluir(int id){
        servico_agendaDao.delete(id);
    }
    
}
