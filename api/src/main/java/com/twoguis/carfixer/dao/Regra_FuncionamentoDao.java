package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Regra_Funcionamento;

@RegisterBeanMapper(Regra_Funcionamento.class)
public interface Regra_FuncionamentoDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into regra_funcionamento (dias, hr_inicio, hr_termino) values (:dias, :hr_inicio, :hr_termino)")
        int insert(@BindBean Regra_Funcionamento regra_funcionamento);

        @SqlQuery("select * " +
                        " from regra_funcionamento;")
        List<Regra_Funcionamento> get();

        @SqlQuery("select * " +
                        " from regra_funcionamento " +
                        " where id = :id;")
        Regra_Funcionamento getById(@Bind("id") int id);

        @SqlUpdate("update regra_funcionamento " +
                        " set dias = :dias, hr_inicio = :hr_inicio, hr_termino = :hr_termino" +
                        " where id = :id;")
        int update(@BindBean Regra_Funcionamento regra_funcionamento);

        @SqlUpdate("delete " +
                        " from regra_funcionamento " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
