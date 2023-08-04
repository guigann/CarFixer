package com.twoguis.carfixer.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.twoguis.carfixer.model.Usuario;

@RegisterBeanMapper(Usuario.class)
public interface UsuarioDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into usuario (nome, cpf, email, telefone, senha, permission) values (:nome, :cpf, :email, :telefone, :senha, :permission)")
        int insert(@BindBean Usuario usuario);

        @SqlQuery("select * " +
                        " from usuario " +
                        " order by nome;")
        List<Usuario> get();

        @SqlQuery("select * " +
                        " from usuario " +
                        " where id = :id;")
        Usuario getById(@Bind("id") int id);

        @SqlQuery("select * " +
                        " from usuario " +
                        " where nome like :nome " +
                        " order by nome;")
        List<Usuario> getByName(@Bind("nome") String nome);

        @SqlQuery("select * " +
                        " from usuario " +
                        " where cpf like :cpf;")
        Usuario getByCpf(@Bind("cpf") String cpf);

        @SqlQuery("select * " +
                        " from usuario " +
                        " where email like :email;")
        Usuario getByEmail(@Bind("email") String email);

        @SqlUpdate("update usuario " +
                        " set nome = :nome, cpf = :cpf, email = :email, " +
                        "     telefone = :telefone, senha = :senha, permission = :permission " +
                        " where id = :id;")
        int update(@BindBean Usuario usuario);

        @SqlUpdate("delete " +
                        " from usuario " +
                        " where id = :id;")
        int delete(@Bind("id") int id);

}
