package com.twoguis.carfixer.dao;

import java.sql.Date;
import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Agendamento;

@RegisterBeanMapper(Agendamento.class)
public interface AgendamentoDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into agendamento (data, id_tipo_servico, status, id_veiculo) values (:data, :id_tipo_servico, :status, :id_veiculo)")
        int insert(@BindBean Agendamento agendamento);

        @SqlQuery("select * " +
                        " from agendamento " +
                        " where id_agendamento = :id_agendamento;")
        Agendamento get(@Bind("id_agendamento") int id_agendamento);

        @SqlQuery("select * " +
                        " from agendamento " +
                        " order by id_tipo_servico;")
        List<Agendamento> getAll();

        @SqlQuery("select * " +
                        " from agendamento " +
                        " where data like :data " +
                        " order by data;")
        List<Agendamento> getAllByData(@Bind("data") Date data);

        @SqlQuery("select * " +
                        " from agendamento " +
                        " where id_veiculo = :id_veiculo " +
                        " order by id_veiculo;")
        List<Agendamento> getAllByVeiculo(@Bind("id_veiculo") int id_veiculo);

        @SqlUpdate("update agendamento " + " set data = :data, " + "id_tipo_servico = :id_tipo_servico," + "status = :status,"
                        + "id_veiculo = :id_veiculo" + " where id_agendamento = :id_agendamento;")
        int update(@BindBean Agendamento agendamento);

        @SqlUpdate("delete " +
                        " from agendamento " +
                        " where id_agendamento = :id_agendamento;")
        int delete(@Bind("id_agendamento") int id_agendamento);

}
