package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Sistema;

@RegisterBeanMapper(Sistema.class)
public interface SistemaDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into sistema (nomeEmpresa, horario_rangeMin, horario_rangeMax, horasPorAgendamento) values (:nomeEmpresa, :horario_rangeMin, :horario_rangeMax, :horasPorAgendamento)")
        int insert(@BindBean Sistema sistema);

        @SqlQuery("select * " +
                        " from sistema " +
                        " order by nomeEmpresa;")
        List<Sistema> get();

        @SqlQuery("select * " +
                        " from sistema " +
                        " where id = :id;")
        Sistema getById(@Bind("id") int id);

        @SqlQuery("select * " +
                        " from sistema " +
                        " where nomeEmpresa like :nomeEmpresa " +
                        " order by nomeEmpresa;")
        List<Sistema> getByName(@Bind("nomeEmpresa") String nomeEmpresa);

        // @SqlQuery("select * " +
        //                 " from sistema " +
        //                 " where horario_rangeMin like :horario_rangeMin;")
        // Sistema getByCpf(@Bind("horario_rangeMin") String horario_rangeMin);

        // @SqlQuery("select * " +
        //                 " from sistema " +
        //                 " where horario_rangeMax like :horario_rangeMax;")
        // Sistema getByEmail(@Bind("horario_rangeMax") String horario_rangeMax);

        @SqlUpdate("update sistema " +
                        " set nomeEmpresa = :nomeEmpresa, horario_rangeMin = :horario_rangeMin, horario_rangeMax = :horario_rangeMax, " +
                        "     horasPorAgendamento = :horasPorAgendamento " +
                        " where id = :id;")
        int update(@BindBean Sistema sistema);

        @SqlUpdate("delete " +
                        " from sistema " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
