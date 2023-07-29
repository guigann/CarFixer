package com.twoguis.carfixer.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twoguis.carfixer.model.Usuario;
import com.twoguis.carfixer.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioService usuarioService, PasswordEncoder encoder) {
        this.usuarioService = usuarioService;
        this.encoder = encoder;
    }

    @GetMapping({ "/", "" })
    public List<Usuario> get() {
        List<Usuario> usuarioList = usuarioService.get();
        return usuarioList;
    }

    @GetMapping("/{id}")
    public Usuario consultarUsuario(@PathVariable("id") int id) {
        Usuario ret = usuarioService.getById(id);
        return ret;
    }

    @PostMapping({ "", "/" })
    public Usuario insert(@RequestBody Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        Usuario ret = usuarioService.insert(usuario);
        return ret;
    }

    @PutMapping({ "", "/" })
    public Usuario update(@RequestBody Usuario usuario) {
        usuarioService.update(usuario);
        return usuario;
    }

    @DeleteMapping("/{id}")
    public Usuario delete(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.getById(id);
        if (usuario == null) {
            throw new RuntimeException("Nao existe usuario com este id para ser excluido....");
        }
        usuarioService.delete(id);
        return usuario;
    }

    @GetMapping("/cpf/{cpf}/exists")
    public int cpfExists(@PathVariable("cpf") String cpf) {
        Usuario usuario = usuarioService.getByCpf(cpf);

        if (usuario != null && usuario.getCpf().equals(cpf)) {
            return 200;
        } else
            return 404;
    }

    @GetMapping("/email/{email}/exists")
    public int emailExists(@PathVariable("email") String email) {
        Usuario usuario = usuarioService.getByEmail(email);

        if (usuario != null && usuario.getEmail().equals(email)) {
            return 200;
        } else
            return 404;
    }

    @GetMapping("/{email}/{senha}/authenticate")
    public int authenticate(@PathVariable("email") String email, @PathVariable("senha") String senha) {
        Usuario usuario = usuarioService.getByEmail(email);

        if (usuario != null && usuario.getEmail().equals(email) && encoder.matches(senha, usuario.getSenha())) {
            return 200;
        } else
            return 401;
    }
}
