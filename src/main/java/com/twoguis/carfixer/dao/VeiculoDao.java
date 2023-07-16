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
        @SqlUpdate("insert into veiculo (placa, modelo, tipo, id_cliente) values (:placa, :modelo, :tipo, :id_cliente)")
        int insert(@BindBean Veiculo veiculo);

        @SqlQuery("select * " +
                        " from veiculo " +
                        " order by modelo;")
        List<Veiculo> get();

        @SqlQuery("select * " +
                        " from veiculo " +
                        " where id_veiculo = :id_veiculo;")
        Veiculo getById(@Bind("id_veiculo") int id_veiculo);

        @SqlQuery("select * " +
                        " from veiculo " +
                        " where placa like :placa;")
        Veiculo getByPlaca(@Bind("placa") String placa);

        @SqlQuery("select * " +
                        " from veiculo " +
                        " where id_cliente = :id_cliente " +
                        " order by id_cliente;")
        List<Veiculo> getByUsuario(@Bind("id_cliente") int id_cliente);

        @SqlUpdate("update veiculo" + " set placa = :placa, " + "modelo = :modelo," + "tipo = :tipo,"
                        + "id_cliente = :id_cliente" + " where id_veiculo = :id_veiculo;")
        int update(@BindBean Veiculo veiculo);

        @SqlUpdate("delete " +
                        " from veiculo " +
                        " where id_veiculo = :id_veiculo;")
        int delete(@Bind("id_veiculo") int id_veiculo);

}
