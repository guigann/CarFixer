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

    public Usuario insert(Usuario usuario) {
        int id_usuario = usuarioDao.insert(usuario);
        usuario.setId(id_usuario);
        return usuario;
    }

    public List<Usuario> get() {
        List<Usuario> usuarios = usuarioDao.get();

        for (Usuario usuario : usuarios) {
            usuario = getVeiculos(usuario);
        }

        return usuarios;
    }

    public Usuario getById(int id) {
        Usuario usuario = usuarioDao.getById(id);

        usuario = getVeiculos(usuario);

        return usuario;
    }

    public Usuario getByCpf(String cpf) {
        return usuarioDao.getByCpf(cpf);
    }

    public Usuario getByEmail(String email) {
        Usuario usuario = usuarioDao.getByEmail(email);

        if (usuario != null) {
            usuario = getVeiculos(usuario);
        }

        return usuario;
    }

    public void update(Usuario usuario) {
        usuarioDao.update(usuario);
    }

    public void delete(int id) {
        usuarioDao.delete(id);
    }

    public Usuario getVeiculos(Usuario usuario) {
        List<Veiculo> veiculos = veiculoDao.getByUsuario(usuario.getId());
        usuario.setVeiculos(veiculos);

        return usuario;
    }

    public static String genPassword() {
        String chars = "0123456789abcdefghijklmnopqrstuvwxyz!@#$%^&*()ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int passwordLength = 12;
        String password = "";
        for (int i = 0; i <= passwordLength; i++) {
            double randomNumber = Math.floor(Math.random() * chars.length());
            password += chars.substring((int) randomNumber, (int) randomNumber + 1);
        }
        return password;
    }
}
