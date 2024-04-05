package com.twoguis.carfixer.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twoguis.carfixer.model.Usuario;
import com.twoguis.carfixer.service.EmailService;
import com.twoguis.carfixer.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
// @RequestMapping("/api/v1/usuario")
@RequestMapping(value = "/api/v1/usuario", produces = {"application/json"})
@Tag(name = "usuario")
@CrossOrigin("*")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final EmailService emailService;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioService usuarioService, EmailService emailService, PasswordEncoder encoder) {
        this.usuarioService = usuarioService;
        this.emailService = emailService;
        this.encoder = encoder;
    }

    @Operation(summary = "Consultar todos os usuários", method = "GET")
    @GetMapping({ "/", "" })
    public List<Usuario> get() {
        List<Usuario> usuarioList = usuarioService.get();
        return usuarioList;
    }

    @Operation(summary = "Consultar usuário por id", method = "GET")
    @GetMapping("/{id}")
    public Usuario consultarUsuario(@PathVariable("id") int id) {
        Usuario ret = usuarioService.getById(id);
        return ret;
    }

    @Operation(summary = "Consultar usuário por email", method = "GET")
    @GetMapping("email/{email}")
    public Usuario getByEmail(@PathVariable("email") String email) {
        Usuario ret = usuarioService.getByEmail(email);
        return ret;
    }

    @Operation(summary = "Inserir usuário", method = "POST")
    @PostMapping({ "", "/" })
    public Usuario insert(@RequestBody Usuario usuario) {
        if (usuario.getSenha().equals("")) {
            usuario.setSenha(UsuarioService.genPassword());
            emailService.sendSimpleMessage(usuario.getEmail(), "Sua senha no CarFixer",
                    "Sua senha gerada no CarFixer foi: " + usuario.getSenha());
        }

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        Usuario ret = usuarioService.insert(usuario);
        return ret;
    }

    @Operation(summary = "Alterar usuário", method = "PUT")
    @PutMapping({ "", "/" })
    public Usuario update(@RequestBody Usuario usuario) {
        usuarioService.update(usuario);
        return usuario;
    }

    @Operation(summary = "Deletar usuário por id", method = "DELETE")
    @DeleteMapping("/{id}")
    public Usuario delete(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.getById(id);
        if (usuario == null) {
            throw new RuntimeException("Nao existe usuario com este id para ser excluido....");
        }
        usuarioService.delete(id);
        return usuario;
    }

    @Operation(summary = "Verificar se o CPF está cadastrado", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CPF encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "CPF não encontrado"),
    })
    @GetMapping("/cpf/{cpf}/exists")
    public int cpfExists(@PathVariable("cpf") String cpf) {
        Usuario usuario = usuarioService.getByCpf(cpf);

        if (usuario != null && usuario.getCpf().equals(cpf)) {
            return 200;
        } else
            return 404;
    }

    @Operation(summary = "Consultar usuário por email", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Email não encontrado"),
    })
    @GetMapping("/email/{email}/exists")
    public int emailExists(@PathVariable("email") String email) {
        Usuario usuario = usuarioService.getByEmail(email);

        if (usuario != null && usuario.getEmail().equals(email)) {
            return 200;
        } else
            return 404;
    }

    @Operation(summary = "Autenticar usuário no sistema", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso"),
            @ApiResponse(responseCode = "401", description = "Autenticação não autorizada"),
    })
    @GetMapping("/{email}/{senha}/authenticate")
    public int authenticate(@PathVariable("email") String email, @PathVariable("senha") String senha) {
        Usuario usuario = usuarioService.getByEmail(email);

        if (usuario != null && usuario.getEmail().equals(email) && encoder.matches(senha, usuario.getSenha())) {
            return 200;
        } else
            return 401;
    }
}
