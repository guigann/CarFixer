package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Agenda;
import com.twoguis.carfixer.model.Servico_Agenda;

@RegisterBeanMapper(Servico_Agenda.class)
@RegisterBeanMapper(Agenda.class)
public interface Servico_AgendaDao {

        @SqlUpdate("insert into servico_agenda (id_servico, id_agenda) values (:id_servico, :id_agenda)")
        void insert(@BindBean Servico_Agenda servico_agenda);

        @SqlQuery("select a.* " +
                        " from agenda a, servico_agenda sa " +
                        " where sa.id_agenda = a.id_agenda " +
                        "   and sa.id_servico = :id_servico " +
                        "   and sa.id_agenda = :id_agenda;")
        Agenda get(@Bind("id_servico") int id_servico, @Bind("id_agenda") int id_agenda);

        @SqlQuery("select a.* " +
                        " from agenda a, servico_agenda sa " +
                        " where sa.id_agenda = a.id_agenda " +
                        "   and sa.id_servico = :id_servico;")
        List<Agenda> getByServico(@Bind("id_servico") int id_servico);

        @SqlUpdate("delete " +
                        " from servico_agenda " +
                        " where id_servico = :id_servico " +
                        "   and id_agenda = :id_agenda;")
        int delete(@Bind("id_servico") int id_servico, @Bind("id_agenda") int id_agenda);

        @SqlUpdate("delete " +
                        " from servico_agenda " +
                        " where id_servico = :id_servico;")
        int deleteByServico(@Bind("id_servico") int id_servico);

}
