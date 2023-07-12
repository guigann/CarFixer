package com.twoguis.carfixer.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.AgendaDao;
import com.twoguis.carfixer.dao.ProdutoDao;
import com.twoguis.carfixer.model.Agenda;
import com.twoguis.carfixer.model.Produto;

@Service
public class AgendaService {

    private final AgendaDao agendaDao;
    private final ProdutoDao produtoDao;

    public AgendaService(Jdbi jdbi) {
        this.agendaDao = jdbi.onDemand(AgendaDao.class);
        this.produtoDao = jdbi.onDemand(ProdutoDao.class);
    }

    public Agenda inserir(Agenda agenda) {
        int id_agenda = agendaDao.insert(agenda);
        agenda.setId_agenda(id_agenda);
        return agenda;
    }

    public List<Agenda> consultarTodos() {
        List<Agenda> agendas = agendaDao.getAll();

        for (Agenda agenda : agendas) {
            agenda = listarProdutos(agenda);
        }

        return agendas;
    }

    public Agenda consultarPorId(int id) {
        Agenda agenda = agendaDao.get(id);
        agenda = listarProdutos(agenda);

        return agenda;
    }

    public void alterar(Agenda agenda) {
        agendaDao.update(agenda);
    }

    public void excluir(int id) {
        agendaDao.delete(id);
    }

    public Agenda listarProdutos(Agenda agenda) {
        List<Produto> produtos = produtoDao.getAllByAgenda(agenda.getId_agenda());
        agenda.setProdutos(produtos);

        return agenda;
    }

}
