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
        @SqlUpdate("insert into veiculo (placa, descricao, tipo, id_usuario) values (:placa, :descricao, :tipo, :id_usuario)")
        int insert(@BindBean Veiculo veiculo);

        @SqlQuery("select * " +
                        " from veiculo " +
                        " where id = :id;")
        Veiculo get(@Bind("id") int id);

        @SqlQuery("select * " +
                        " from veiculo " +
                        " order by descricao;")
        List<Veiculo> getAll();

        @SqlQuery("select * " +
                        " from veiculo " +
                        " where descricao like :descricao " +
                        " order by descricao;")
        List<Veiculo> getAllByDescricao(@Bind("descricao") String descricao);

        @SqlUpdate("update veiculo " + " set placa = :placa, " + "descricao = :descricao," + "tipo = :tipo,"
                        + "id_usuario = :id_usuario," + " where id = :id;")
        int update(@BindBean Veiculo veiculo);

        @SqlUpdate("delete " +
                        " from veiculo " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
