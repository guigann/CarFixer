package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Item_Servico;

@RegisterBeanMapper(Item_Servico.class)
public interface Item_ServicoDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into item_servico (descricao, valor, id_servico) values (:descricao, :valor, :id_servico)")
        int insert(@BindBean Item_Servico item_servico);

        @SqlQuery("select * " +
                        " from item_servico " +
                        " where id = :id;")
        Item_Servico get(@Bind("id") int id);

        @SqlQuery("select * " +
                        " from item_servico " +
                        " order by valor;")
        List<Item_Servico> getAll();

        @SqlQuery("select * " +
                        " from item_servico " +
                        " where valor like :valor " +
                        " order by valor;")
        List<Item_Servico> getAllByObjetivo(@Bind("valor") String valor);

        @SqlUpdate("update item_servico " + " set descricao = :descricao, " + "valor = :valor"
                        + "id_servico = :id_servico," + " where id = :id;")
        int update(@BindBean Item_Servico item_servico);

        @SqlUpdate("delete " +
                        " from item_servico " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
