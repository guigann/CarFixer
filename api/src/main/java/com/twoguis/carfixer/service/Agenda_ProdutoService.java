package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.Agenda_ProdutoDao;
import com.twoguis.carfixer.model.Agenda_Produto;
import com.twoguis.carfixer.model.Produto;

@Service
public class Agenda_ProdutoService extends ProdutoService {

    private final Agenda_ProdutoDao agenda_produtoDao;

    public Agenda_ProdutoService(Jdbi jdbi) {
        super(jdbi);
        this.agenda_produtoDao = jdbi.onDemand(Agenda_ProdutoDao.class);
    }

    public Agenda_Produto insert(Agenda_Produto agenda_produto) {
        agenda_produtoDao.insert(agenda_produto);
        return agenda_produto;
    }

    public List<Produto> getByProdutoAgenda(int id_agenda) {
        return agenda_produtoDao.getByProdutoAgenda(id_agenda);
    }

    public Produto get(int id_agenda, int id_produto) {
      return agenda_produtoDao.get(id_agenda, id_produto);
    }

    public void delete(int id_agenda, int id_produto) {
        agenda_produtoDao.delete(id_agenda, id_produto);
    }

    public void deleteByProdutoAgenda(int id_agenda) {
        agenda_produtoDao.deleteByProdutoAgenda(id_agenda);
    }

}
