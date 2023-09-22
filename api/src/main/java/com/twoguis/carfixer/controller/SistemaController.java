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

@RestController
@RequestMapping("/api/v1/sistema")
@CrossOrigin("*")
public class SistemaController {
    private final SistemaService sistemaService;

    public SistemaController(SistemaService sistemaService) {
        this.sistemaService = sistemaService;
    }

    @GetMapping({ "/", "" })
    public List<Sistema> get() {
        List<Sistema> sistemaList = sistemaService.get();
        return sistemaList;
    }

    @GetMapping("/{id}")
    public Sistema consultarSistema(@PathVariable("id") int id) {
        Sistema ret = sistemaService.getById(id);
        return ret;
    }

    @PostMapping({ "", "/" })
    public Sistema insert(@RequestBody Sistema sistema) {
        Sistema ret = sistemaService.insert(sistema);
        return ret;
    }

    @PutMapping({ "", "/" })
    public Sistema update(@RequestBody Sistema sistema) {
        sistemaService.update(sistema);
        return sistema;
    }

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
