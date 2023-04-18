package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.UsuarioDao;
import com.twoguis.carfixer.model.Usuario;

@Service
public class UsuarioService {
    
    private final UsuarioDao usuarioDao;
    
    public UsuarioService(Jdbi jdbi){
        this.usuarioDao = jdbi.onDemand(UsuarioDao.class);
    }
    
    public Usuario inserir (Usuario usuario){
        int idUsuario = usuarioDao.insert(usuario);
        usuario.setId(idUsuario);
        return usuario;
    }
    
    public List<Usuario> consultarTodos(){
        return usuarioDao.getAll();
    }

    public Usuario consultarPorId(int id){
        return usuarioDao.get(id);
    }
    
    public void alterar(Usuario usuario){
        usuarioDao.update(usuario);
    }
    
    public void excluir(int id){
        usuarioDao.delete(id);
    }
    
}
