package com.twoguis.carfixer.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.AgendamentoDao;
import com.twoguis.carfixer.dao.ServicoDao;
import com.twoguis.carfixer.model.Agendamento;
import com.twoguis.carfixer.model.Servico;

@Service
public class AgendamentoService {
    
    private final AgendamentoDao agendamentoDao;
    private final ServicoDao servicoDao;
    
    public AgendamentoService(Jdbi jdbi){
        this.agendamentoDao = jdbi.onDemand(AgendamentoDao.class);
        this.servicoDao = jdbi.onDemand(ServicoDao.class);
    }
    
    public Agendamento inserir (Agendamento agendamento){
        int id_agendamento = agendamentoDao.insert(agendamento);
        agendamento.setId_agendamento(id_agendamento);
        return agendamento;
    }
    
    public List<Agendamento> consultarTodos(){
        List<Agendamento> agendamentos = agendamentoDao.getAll();

        for (Agendamento agendamento : agendamentos) {
            List<Servico> servicos = servicoDao.getAllByAgendamento(agendamento.getId_agendamento());
            agendamento.setServicos(servicos);
        }

        return agendamentos;
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
