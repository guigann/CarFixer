package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.Servico_AgendaDao;
import com.twoguis.carfixer.model.Agenda;
import com.twoguis.carfixer.model.Servico_Agenda;

@Service
public class Servico_AgendaService extends AgendaService {

    private final Servico_AgendaDao servico_agendaDao;

    public Servico_AgendaService(Jdbi jdbi) {
        super(jdbi);
        this.servico_agendaDao = jdbi.onDemand(Servico_AgendaDao.class);
    }

    public Servico_Agenda insert(Servico_Agenda servico_agenda) {
        servico_agendaDao.insert(servico_agenda);
        return servico_agenda;
    }

    public List<Agenda> getByServico(int id_servico) {
        List<Agenda> agendaList = servico_agendaDao.getByServico(id_servico);

        for (Agenda agenda : agendaList) {
            agenda = this.getProdutos(agenda);
        }

        return agendaList;
    }

    public Agenda get(int id_servico, int id_agenda) {
        Agenda agenda = servico_agendaDao.get(id_servico, id_agenda);
        agenda = this.getProdutos(agenda);

        return agenda;
    }

    public void delete(int id_servico, int id_agenda) {
        servico_agendaDao.delete(id_servico, id_agenda);
    }

    public void deleteByServico(int id_servico) {
        servico_agendaDao.deleteByServico(id_servico);
    }

}
