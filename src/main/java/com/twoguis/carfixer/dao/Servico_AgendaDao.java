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
public interface Servico_AgendaDao {

                
    @SqlUpdate("insert into servico_agenda (id_servico, id_agenda) values (:id_servico, :id_agenda)")
    void insert(@BindBean Servico_Agenda servico_agenda);
    
    
    @SqlQuery("select * " +
            " from agenda a, servico_agenda sa " +
            " where sa.id_agenda = a.id_agenda " +
            "   and sa.id_servico = :id_servico " +
            "   and sa.id_agenda = :id_agenda;")
    Agenda get(@Bind("id_servico") int id_servico, @Bind("id_agenda") int id_agenda);

    @SqlQuery("select * " +
            " from agenda a, servico_agenda sa " +
            " where sa.id_agenda = a.id_agenda " +
            "   and sa.id_servico = :id_servico;")
    List<Agenda> getAllByServico(@Bind("id_servico") int id_servico);
    
    @SqlUpdate("delete " +
            " from servico_agenda " +
            " where sa.id_servico = :id_servico " +
            "   and sa.id_agenda = :id_agenda;")
    int delete(@Bind("id_servico") int id_servico, @Bind("id_agenda") int id_agenda);
    
    @SqlUpdate("delete " +
            " from servico_agenda " +
            " where sa.id_servico = :id_servico;")
    int deleteAllByServico(@Bind("id_servico") int id_servico);

}
