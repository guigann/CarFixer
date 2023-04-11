package com.twoguis.carfixer.dao;

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
        @SqlUpdate("insert into agendamento (data, objetivo, unidade, status, id_veiculo) values (:data, :objetivo, :unidade, :status, :veiculo.getId())")
        int insert(@BindBean Agendamento agendamento);

        @SqlQuery("select * " +
                        " from agendamento " +
                        " where id = :id;")
        Agendamento get(@Bind("id") int id);

        @SqlQuery("select * " +
                        " from agendamento " +
                        " order by objetivo;")
        List<Agendamento> getAll();

        @SqlQuery("select * " +
                        " from agendamento " +
                        " where objetivo like :objetivo " +
                        " order by objetivo;")
        List<Agendamento> getAllByObjetivo(@Bind("objetivo") String objetivo);

        @SqlUpdate("update agendamento " + " set data = :data, " + "objetivo = :objetivo," +"unidade = :unidade," + "status = :status,"
                        + "id_veiculo = :veiculo.getId()," + " where id = :id;")
        int update(@BindBean Agendamento agendamento);

        @SqlUpdate("delete " +
                        " from agendamento " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
