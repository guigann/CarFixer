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
        @SqlUpdate("insert into servico (dataPrevEntrega, observacao, id_agendamento) values (:dataPrevEntrega, :observacao, :id_agendamento)")
        int insert(@BindBean Servico servico);

        @SqlQuery("select * " +
                        " from servico " +
                        " where id = :id;")
        Servico get(@Bind("id") int id);

        @SqlQuery("select * " +
                        " from servico " +
                        " order by observacao;")
        List<Servico> getAll();

        @SqlQuery("select * " +
                        " from servico " +
                        " where observacao like :observacao " +
                        " order by observacao;")
        List<Servico> getAllByObjetivo(@Bind("observacao") String observacao);

        @SqlUpdate("update servico " + " set dataPrevEntrega = :dataPrevEntrega, " + "observacao = :observacao"
                        + "id_agendamento = :agendamento.getId()," + " where id = :id;")
        int update(@BindBean Servico servico);

        @SqlUpdate("delete " +
                        " from servico " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
