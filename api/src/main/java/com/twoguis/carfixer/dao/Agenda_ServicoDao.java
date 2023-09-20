package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Agenda_Servico;
import com.twoguis.carfixer.model.Servico;

@RegisterBeanMapper(Agenda_Servico.class)
@RegisterBeanMapper(Servico.class)
public interface Agenda_ServicoDao {

        @SqlUpdate("insert into servico_agenda (id_agenda, id_servico, observacao) values (:id_agenda, :id, :observacao)")
        void insert(@BindBean Agenda_Servico agenda_servico);

        @SqlQuery("select s.*, sa.observacao from servico s, servico_agenda sa where sa.id_servico = s.id and sa.id_agenda = :id_agenda and sa.id_servico = :id_servico;")
        Servico get(@Bind("id_agenda") int id_agenda, @Bind("id_servico") int id_servico);

        @SqlQuery("select s.*, sa.observacao from servico s, servico_agenda sa where sa.id_servico = s.id and sa.id_agenda = :id_agenda;")
        List<Servico> getByServicoAgenda(@Bind("id_agenda") int id_agenda);

        @SqlUpdate("delete " +
                        " from servico_agenda " +
                        " where id_agenda = :id_agenda " +
                        " and id_servico = :id_servico;")
        int delete(@Bind("id_agenda") int id_agenda, @Bind("id_servico") int id_servico);

        @SqlUpdate("delete " +
                        " from servico_agenda " +
                        " where id_agenda = :id_agenda;")
        int deleteByServicoAgenda(@Bind("id_agenda") int id_agenda);

}
