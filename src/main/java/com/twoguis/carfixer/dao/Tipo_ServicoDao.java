package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Tipo_Servico;

@RegisterBeanMapper(Tipo_Servico.class)
public interface Tipo_ServicoDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into tipo_servico (nome, imagem, descricao) values (:nome, :imagem, :descricao)")
        int insert(@BindBean Tipo_Servico tipo_servico);

        @SqlQuery("select * " +
                        " from tipo_servico " +
                        " where id = :id;")
        Tipo_Servico get(@Bind("id") int id);

        @SqlQuery("select * " +
                        " from tipo_servico " +
                        " order by imagem;")
        List<Tipo_Servico> getAll();

        @SqlQuery("select * " +
                        " from tipo_servico " +
                        " where nome like :nome " +
                        " order by nome;")
        List<Tipo_Servico> getAllByNome(@Bind("nome") String nome);

        @SqlUpdate("update tipo_servico " + " set nome = :nome, " + "imagem = :imagem"
                        + "descricao = :descricao," + " where id = :id;")
        int update(@BindBean Tipo_Servico tipo_servico);

        @SqlUpdate("delete " +
                        " from tipo_servico " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
