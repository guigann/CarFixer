package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Veiculo;

@RegisterBeanMapper(Veiculo.class)
public interface VeiculoDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into veiculo (placa, modelo, tipo, id_usuario) values (:placa, :modelo, :tipo, :id_usuario)")
        int insert(@BindBean Veiculo veiculo);

        @SqlQuery("select * " +
                        " from veiculo " +
                        " where id = :id;")
        Veiculo get(@Bind("id") int id);

        @SqlQuery("select * " +
                        " from veiculo " +
                        " order by modelo;")
        List<Veiculo> getAll();

        @SqlQuery("select * " +
                        " from veiculo " +
                        " where modelo like :modelo " +
                        " order by modelo;")
        List<Veiculo> getAllBymodelo(@Bind("modelo") String modelo);

        @SqlUpdate("update veiculo " + " set placa = :placa, " + "modelo = :modelo," + "tipo = :tipo,"
                        + "id_usuario = :id_usuario," + " where id = :id;")
        int update(@BindBean Veiculo veiculo);

        @SqlUpdate("delete " +
                        " from veiculo " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
