package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Horario;

@RegisterBeanMapper(Horario.class)
public interface HorarioDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into horario (status, data) values (:status, :data)")
        int insert(@BindBean Horario horario);

        @SqlQuery("select * " +
                        " from horario " +
                        " order by data;")
        List<Horario> get();

        @SqlQuery("select * " +
                        " from horario " +
                        " where id = :id;")
        Horario getById(@Bind("id") int id);

        @SqlQuery("select * " +
                        " from horario " +
                        " where status like :status " +
                        " order by status;")
        List<Horario> getByNome(@Bind("status") String status);

        @SqlUpdate("update horario " + " set status = :status, " + "data = :data" + " where id = :id;")
        int update(@BindBean Horario horario);

        @SqlUpdate("delete " +
                        " from horario " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
