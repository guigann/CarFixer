package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

// import com.twoguis.carfixer.dao.AgendaDao;
import com.twoguis.carfixer.dao.ServicoDao;
// import com.twoguis.carfixer.model.Agenda;
import com.twoguis.carfixer.model.Servico;

@Service
public class ServicoService {
    
    private final ServicoDao servicoDao;
    // private final AgendaDao agendaDao;
    
    public ServicoService(Jdbi jdbi){
        this.servicoDao = jdbi.onDemand(ServicoDao.class);
        // this.agendaDao = jdbi.onDemand(AgendaDao.class);
    }
    
    public Servico insert (Servico servico){
        int id_servico = servicoDao.insert(servico);
        servico.setId(id_servico);
        return servico;
    }
    
    public List<Servico> get(){
        List<Servico> servicos = servicoDao.get();

        // for (Servico servico : servicos) {
        //     List<Agenda> agendas = agendaDao.getByServico(servico.getId_servico());
        //     servico.setAgendas(agendas);
        // }

        return servicos;
    }

    public Servico getById(int id){
        return servicoDao.getById(id);
    }
    
    public void update(Servico servico){
        servicoDao.update(servico);
    }
    
    public void delete(int id){
        servicoDao.delete(id);
    }
    
}
