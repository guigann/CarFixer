package com.twoguis.carfixer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twoguis.carfixer.model.Sistema;
import com.twoguis.carfixer.service.SistemaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
// @RequestMapping("/api/v1/sistema")
@RequestMapping(value = "/api/v1/sistema", produces = {"application/json"})
@Tag(name = "sistema")
@CrossOrigin("*")
public class SistemaController {
    private final SistemaService sistemaService;

    public SistemaController(SistemaService sistemaService) {
        this.sistemaService = sistemaService;
    }

    @Operation(summary = "Consultar todos os sistemas", method = "GET")
    @GetMapping({ "/", "" })
    public List<Sistema> get() {
        List<Sistema> sistemaList = sistemaService.get();
        return sistemaList;
    }

    @Operation(summary = "Consultar sistema por id", method = "GET")
    @GetMapping("/{id}")
    public Sistema consultarSistema(@PathVariable("id") int id) {
        Sistema ret = sistemaService.getById(id);
        return ret;
    }

    @Operation(summary = "Inserir sistema", method = "POST")
    @PostMapping({ "", "/" })
    public Sistema insert(@RequestBody Sistema sistema) {
        Sistema ret = sistemaService.insert(sistema);
        return ret;
    }

    @Operation(summary = "Atualizar sistema", method = "PUT")
    @PutMapping({ "", "/" })
    public Sistema update(@RequestBody Sistema sistema) {
        sistemaService.update(sistema);
        return sistema;
    }

    @Operation(summary = "Deletar sistema por id", method = "DELETE")
    @DeleteMapping("/{id}")
    public Sistema delete(@PathVariable("id") int id) {
        Sistema sistema = sistemaService.getById(id);
        if (sistema == null) {
            throw new RuntimeException("Nao existe sistema com este id para ser excluido....");
        }
        sistemaService.delete(id);
        return sistema;
    }
}
