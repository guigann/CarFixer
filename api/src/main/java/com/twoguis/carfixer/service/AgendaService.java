package com.twoguis.carfixer.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.AgendaDao;
import com.twoguis.carfixer.dao.Agenda_ProdutoDao;
import com.twoguis.carfixer.model.Agenda;
import com.twoguis.carfixer.model.Produto;

@Service
public class AgendaService {

    private final AgendaDao agendaDao;
    private final Agenda_ProdutoDao agenda_produtoDao;

    public AgendaService(Jdbi jdbi) {
        this.agendaDao = jdbi.onDemand(AgendaDao.class);
        this.agenda_produtoDao = jdbi.onDemand(Agenda_ProdutoDao.class);
    }

    public Agenda insert(Agenda agenda) {
        int id_agenda = agendaDao.insert(agenda);
        agenda.setId(id_agenda);
        return agenda;
    }

    public List<Agenda> get() {
        List<Agenda> agendas = agendaDao.get();

        for (Agenda agenda : agendas) {
            agenda = getProdutos(agenda);
        }

        return agendas;
    }

    public Agenda getById(int id) {
        Agenda agenda = agendaDao.getById(id);
        agenda = getProdutos(agenda);

        return agenda;
    }

    public List<Agenda> getByVeiculo(int id) {
        List<Agenda> agendas = agendaDao.getByVeiculo(id);

        for (Agenda agenda : agendas) {
            agenda = getProdutos(agenda);
        }

        return agendas;
    }

    public void update(Agenda agenda) {
        agendaDao.update(agenda);
    }

    public void delete(int id) {
        agendaDao.delete(id);
    }

    public Agenda getProdutos(Agenda agenda) {
        List<Produto> produtos = agenda_produtoDao.getByProdutoAgenda(agenda.getId());
        agenda.setProdutos(produtos);

        return agenda;
    }

}
