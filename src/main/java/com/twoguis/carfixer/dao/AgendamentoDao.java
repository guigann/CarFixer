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
        @SqlUpdate("insert into agendamento (data, tipo_servico, status, id_veiculo) values (:data, :tipo_servico, :status, :id_veiculo)")
        int insert(@BindBean Agendamento agendamento);

        @SqlQuery("select * " +
                        " from agendamento " +
                        " where id = :id;")
        Agendamento get(@Bind("id") int id);

        @SqlQuery("select * " +
                        " from agendamento " +
                        " order by tipo_servico;")
        List<Agendamento> getAll();

        @SqlQuery("select * " +
                        " from agendamento " +
                        " where data like :data " +
                        " order by data;")
        List<Agendamento> getAllByData(@Bind("data") Date data);

        @SqlUpdate("update agendamento " + " set data = :data, " + "tipo_servico = :tipo_servico," + "status = :status,"
                        + "id_veiculo = :id_veiculo," + " where id = :id;")
        int update(@BindBean Agendamento agendamento);

        @SqlUpdate("delete " +
                        " from agendamento " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
