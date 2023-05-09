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
            " where id_usuario = :id_usuario;")
    Usuario get(@Bind("id_usuario") int id_usuario);

    @SqlQuery("select * " +
            " from usuario " +
            " order by nome;")
    List<Usuario> getAll();

    @SqlQuery("select * " +
            " from usuario " +
            " where nome like :nome " +
            " order by nome;")
    List<Usuario> getAllByName(@Bind("nome") String nome);

    @SqlUpdate("update usuario " +
            " set nome = :nome, cpf = :cpf, email = :email, " +
            "     telefone = :telefone, senha = :senha, permission = :permission " +
            " where id_usuario = :id_usuario;")
    int update(@BindBean Usuario usuario);

    @SqlUpdate("delete " +
            " from usuario " +
            " where id_usuario = :id_usuario;")
    int delete(@Bind("id_usuario") int id_usuario);

}
