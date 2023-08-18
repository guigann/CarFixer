package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Servico;

@RegisterBeanMapper(Servico.class)
public interface ServicoDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into servico (nome, descricao) values (:nome, :descricao)")
        int insert(@BindBean Servico servico);

        @SqlQuery("select * " +
                        " from servico " +
                        " order by nome;")
        List<Servico> get();

        @SqlQuery("select * " +
                        " from servico " +
                        " where id = :id;")
        Servico getById(@Bind("id") int id);

        @SqlUpdate("update servico" + " set nome = :nome, " + "descricao = :descricao"
                        + " where id = :id;")
        int update(@BindBean Servico servico);

        @SqlUpdate("delete " +
                        " from servico " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
