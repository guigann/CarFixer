package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.Agenda_ServicoDao;
import com.twoguis.carfixer.model.Agenda_Servico;
import com.twoguis.carfixer.model.Servico;

@Service
public class Agenda_ServicoService extends ServicoService {

    private final Agenda_ServicoDao agenda_servicoDao;

    public Agenda_ServicoService(Jdbi jdbi) {
        super(jdbi);
        this.agenda_servicoDao = jdbi.onDemand(Agenda_ServicoDao.class);
    }

    public Agenda_Servico insert(Agenda_Servico agenda_servico) {
        agenda_servicoDao.insert(agenda_servico);
        return agenda_servico;
    }

    public List<Servico> getByServicoAgenda(int id_agenda) {
        return agenda_servicoDao.getByServicoAgenda(id_agenda);
    }

    public Servico get(int id_agenda, int id_servico) {
      return agenda_servicoDao.get(id_agenda, id_servico);
    }

    public void delete(int id_agenda, int id_servico) {
        agenda_servicoDao.delete(id_agenda, id_servico);
    }

    public void deleteByServicoAgenda(int id_agenda) {
        agenda_servicoDao.deleteByServicoAgenda(id_agenda);
    }

}
