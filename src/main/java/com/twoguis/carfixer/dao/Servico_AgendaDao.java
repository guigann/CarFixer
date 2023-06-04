package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Servico_Agenda;

@RegisterBeanMapper(Servico_Agenda.class)
public interface Servico_AgendaDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into servico_agenda (id_servico, id_agenda, observacao) values (:id_servico, :id_agenda, :observacao)")
        int insert(@BindBean Servico_Agenda servico_agenda);

        @SqlQuery("select * " +
                        " from servico_agenda " +
                        " where id_servico_agenda = :id_servico_agenda;")
        Servico_Agenda get(@Bind("id_servico_agenda") int id_servico_agenda);

        @SqlQuery("select * " +
                        " from servico_agenda " +
                        " order by id_agenda;")
        List<Servico_Agenda> getAll();

        @SqlUpdate("update servico_agenda " + " set id_servico = :id_servico, " + "id_agenda = :id_agenda,"
                        + "observacao = :observacao" + " where id_servico_agenda = :id_servico_agenda;")
        int update(@BindBean Servico_Agenda servico_agenda);

        @SqlUpdate("delete " +
                        " from servico_agenda " +
                        " where id_servico_agenda = :id_servico_agenda;")
        int delete(@Bind("id_servico_agenda") int id_servico_agenda);

}
