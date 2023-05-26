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
                        " where id_veiculo = :id_veiculo;")
        Veiculo get(@Bind("id_veiculo") int id_veiculo);

        @SqlQuery("select * " +
                        " from veiculo " +
                        " order by modelo;")
        List<Veiculo> getAll();

        @SqlQuery("select * " +
                        " from veiculo " +
                        " where modelo like :modelo " +
                        " order by modelo;")
        List<Veiculo> getAllBymodelo(@Bind("modelo") String modelo);

        @SqlQuery("select * " +
                        " from veiculo " +
                        " where id_usuario = :id_usuario " +
                        " order by id_usuario;")
        List<Veiculo> getAllByUsuario(@Bind("id_usuario") int id_usuario);

        @SqlUpdate("update veiculo" + " set placa = :placa, " + "modelo = :modelo," + "tipo = :tipo,"
                        + "id_usuario = :id_usuario" + " where id_veiculo = :id_veiculo;")
        int update(@BindBean Veiculo veiculo);

        @SqlUpdate("delete " +
                        " from veiculo " +
                        " where id_veiculo = :id_veiculo;")
        int delete(@Bind("id_veiculo") int id_veiculo);

}
