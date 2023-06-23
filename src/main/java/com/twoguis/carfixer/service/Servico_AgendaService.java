package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.Servico_AgendaDao;
import com.twoguis.carfixer.model.Agenda;
import com.twoguis.carfixer.model.Servico_Agenda;

@Service
public class Servico_AgendaService {
    
    private final Servico_AgendaDao servico_agendaDao;
    
    public Servico_AgendaService(Jdbi jdbi){
        this.servico_agendaDao = jdbi.onDemand(Servico_AgendaDao.class);
    }
    
    public Servico_Agenda inserir (Servico_Agenda servico_agenda){
        servico_agendaDao.insert(servico_agenda);
        return servico_agenda;
    }
    
    public List<Agenda> getByServico(int id_servico){
        List<Agenda> agendaList = servico_agendaDao.getAllByServico(id_servico);
        return agendaList;
    }
    
    public Agenda get(int id_servico, int id_agenda){
        Agenda agenda = servico_agendaDao.get(id_servico, id_agenda);
        return agenda;
    }
    
    public void delete(int id_servico, int id_agenda){
        servico_agendaDao.delete(id_servico, id_agenda);
    }
    
    public void deleteAllByServico(int id_servico){
        servico_agendaDao.deleteAllByServico(id_servico);
    }
    
}
