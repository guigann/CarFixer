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
            usuario = listarVeiculos(usuario);
        }

        return usuarios;
    }

    public Usuario consultarPorId(int id) {
        Usuario usuario = usuarioDao.get(id);

        usuario = listarVeiculos(usuario);

        return usuario;
    }

    public void alterar(Usuario usuario) {
        usuarioDao.update(usuario);
    }

    public void excluir(int id) {
        usuarioDao.delete(id);
    }

    public Usuario listarVeiculos(Usuario usuario) {
        List<Veiculo> veiculos = veiculoDao.getAllByUsuario(usuario.getId_usuario());
        usuario.setVeiculos(veiculos);

        return usuario;
    }
}
