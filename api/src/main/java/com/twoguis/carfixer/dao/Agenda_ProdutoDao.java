package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Agenda_Produto;
import com.twoguis.carfixer.model.Produto;

@RegisterBeanMapper(Agenda_Produto.class)
@RegisterBeanMapper(Produto.class)
public interface Agenda_ProdutoDao {

        @SqlUpdate("insert into produto_agenda (id_agenda, id_produto) values (:id_agenda, :id)")
        void insert(@BindBean Agenda_Produto agenda_produto);

        @SqlQuery("select p.* from produto p, produto_agenda pa where sa.id_produto = p.id and pa.id_agenda = :id_agenda and pa.id_produto = :id_produto;")
        Produto get(@Bind("id_agenda") int id_agenda, @Bind("id_produto") int id_produto);

        @SqlQuery("select p.* from produto p, produto_agenda pa where pa.id_produto = p.id and pa.id_agenda = :id_agenda;")
        List<Produto> getByProdutoAgenda(@Bind("id_agenda") int id_agenda);

        @SqlUpdate("delete " +
                        " from produto_agenda " +
                        " where id_agenda = :id_agenda " +
                        " and id_produto = :id_produto;")
        int delete(@Bind("id_agenda") int id_agenda, @Bind("id_produto") int id_produto);

        @SqlUpdate("delete " +
                        " from produto_agenda " +
                        " where id_agenda = :id_agenda;")
        int deleteByProdutoAgenda(@Bind("id_agenda") int id_agenda);

}
