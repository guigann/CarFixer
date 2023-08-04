package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Agenda;

@RegisterBeanMapper(Agenda.class)
public interface AgendaDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into agenda (id_horario, id_veiculo, status, dt_previsao, dt_fim, observacao) values (:id_horario, :id_veiculo, :status, :dt_previsao, :dt_fim, :observacao)")
        int insert(@BindBean Agenda agenda);

        @SqlQuery("select * " +
                        " from agenda " +
                        " order by id_veiculo;")
        List<Agenda> get();

        @SqlQuery("select * " +
                        " from agenda " +
                        " where id = :id;")
        Agenda getById(@Bind("id") int id);

        @SqlQuery("select * " +
                        " from agenda " +
                        " where id_veiculo = :id_veiculo " +
                        " order by id_veiculo;")
        List<Agenda> getByVeiculo(@Bind("id_veiculo") int id_veiculo);

        @SqlQuery("select * " +
                        " from agenda " +
                        " where id_servico = :id_servico " +
                        " order by id_servico;")
        List<Agenda> getByServico(@Bind("id_servico") int id_servico);

        @SqlUpdate("update agenda" + " set id_horario = :id_horario, " + "id_veiculo = :id_veiculo,"
                        + "status = :status,"
                        + "dt_previsao = :dt_previsao," + "dt_fim = :dt_fim," + "observacao = :observacao"
                        + " where id = :id;")
        int update(@BindBean Agenda agenda);

        @SqlUpdate("delete " +
                        " from agenda " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
