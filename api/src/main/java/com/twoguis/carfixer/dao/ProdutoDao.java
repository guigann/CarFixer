package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Produto;

@RegisterBeanMapper(Produto.class)
public interface ProdutoDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into produto (descricao) values (:descricao)")
        int insert(@BindBean Produto produto);

        @SqlQuery("select * " +
                        " from produto " +
                        " order by descricao;")
        List<Produto> get();

        @SqlQuery("select * " +
                        " from produto " +
                        " where id = :id;")
        Produto getById(@Bind("id") int id);

        @SqlUpdate("update produto " + " set descricao = :descricao"
                        + " where id = :id;")
        int update(@BindBean Produto produto);

        @SqlUpdate("delete " +
                        " from produto " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
