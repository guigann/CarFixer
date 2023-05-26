package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.UsuarioDao;
import com.twoguis.carfixer.dao.VeiculoDao;
import com.twoguis.carfixer.model.Usuario;
import com.twoguis.carfixer.model.Veiculo;

@Service
public class UsuarioService {

    private final UsuarioDao usuarioDao;
    private final VeiculoDao veiculoDao;

    public UsuarioService(Jdbi jdbi) {
        this.usuarioDao = jdbi.onDemand(UsuarioDao.class);
        this.veiculoDao = jdbi.onDemand(VeiculoDao.class);
    }

    public Usuario inserir(Usuario usuario) {
        int id_usuario = usuarioDao.insert(usuario);
        usuario.setId_usuario(id_usuario);
        return usuario;
    }

    public List<Usuario> consultarTodos() {
        List<Usuario> usuarios = usuarioDao.getAll();

        for (Usuario usuario : usuarios) {
            List<Veiculo> veiculos = veiculoDao.getAllByUsuario(usuario.getId_usuario());
            usuario.setVeiculos(veiculos);
        }

        return usuarios;
    }

    public Usuario consultarPorId(int id) {
        return usuarioDao.get(id);
    }

    public void alterar(Usuario usuario) {
        usuarioDao.update(usuario);
    }

    public void excluir(int id) {
        usuarioDao.delete(id);
    }

}
