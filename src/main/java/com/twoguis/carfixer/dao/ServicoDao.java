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
        @SqlUpdate("insert into servico (dataPrevEntrega, observacao, id_agendamento, mecanico) values (:dataPrevEntrega, :observacao, :id_agendamento, :mecanico)")
        int insert(@BindBean Servico servico);

        @SqlQuery("select * " +
                        " from servico " +
                        " where id_servico = :id_servico;")
        Servico get(@Bind("id_servico") int id_servico);

        @SqlQuery("select * " +
                        " from servico " +
                        " order by observacao;")
        List<Servico> getAll();

        @SqlQuery("select * " +
                        " from servico " +
                        " where observacao like :observacao " +
                        " order by observacao;")
        List<Servico> getAllByObjetivo(@Bind("observacao") String observacao);

        @SqlQuery("select * " +
                        " from servico " +
                        " where id_agendamento like :id_agendamento " +
                        " order by id_agendamento;")
        List<Servico> getAllByAgendamento(@Bind("id_agendamento") int id_agendamento);

        @SqlUpdate("update servico" + " set dataPrevEntrega = :dataPrevEntrega, " + "observacao = :observacao,"
                        + "id_agendamento = :id_agendamento," + "mecanico = :mecanico" + " where id_servico = :id_servico;")
        int update(@BindBean Servico servico);

        @SqlUpdate("delete " +
                        " from servico " +
                        " where id_servico = :id_servico;")
        int delete(@Bind("id_servico") int id_servico);

}
