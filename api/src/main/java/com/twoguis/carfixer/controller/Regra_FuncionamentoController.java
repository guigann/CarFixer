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

import com.twoguis.carfixer.model.Regra_Funcionamento;
import com.twoguis.carfixer.service.Regra_FuncionamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
// @RequestMapping("/api/v1/regra_funcionamento")
@RequestMapping(value = "/api/v1/regra_funcionamento", produces = {"application/json"})
@Tag(name = "regra_funcionamento")
@CrossOrigin("*")
public class Regra_FuncionamentoController {
    private final Regra_FuncionamentoService regra_funcionamentoService;

    public Regra_FuncionamentoController(Regra_FuncionamentoService regra_funcionamentoService) {
        this.regra_funcionamentoService = regra_funcionamentoService;
    }

    @Operation(summary = "Consultar todos as regras de funcionamentos", method = "GET")
    @GetMapping({ "/", "" })
    public List<Regra_Funcionamento> get() {
        List<Regra_Funcionamento> regra_funcionamentoList = regra_funcionamentoService.get();
        return regra_funcionamentoList;
    }

    @Operation(summary = "Consultar regra de funcionamento por id", method = "GET")
    @GetMapping("/{id}")
    public Regra_Funcionamento consultarRegra_Funcionamento(@PathVariable("id") int id) {
        Regra_Funcionamento ret = regra_funcionamentoService.getById(id);
        return ret;
    }

    @Operation(summary = "Inserir regra de funcionamento", method = "POST")
    @PostMapping({ "", "/" })
    public Regra_Funcionamento insert(@RequestBody Regra_Funcionamento regra_funcionamento) {
        Regra_Funcionamento ret = regra_funcionamentoService.insert(regra_funcionamento);
        return ret;
    }

    @Operation(summary = "Atualizar regra de funcionamento", method = "PUT")
    @PutMapping({ "", "/" })
    public Regra_Funcionamento update(@RequestBody Regra_Funcionamento regra_funcionamento) {
        regra_funcionamentoService.update(regra_funcionamento);
        return regra_funcionamento;
    }

    @Operation(summary = "Deletar regra de funcionamento por id", method = "DELETE")
    @DeleteMapping("/{id}")
    public Regra_Funcionamento delete(@PathVariable("id") int id) {
        Regra_Funcionamento regra_funcionamento = regra_funcionamentoService.getById(id);
        if (regra_funcionamento == null) {
            throw new RuntimeException("Nao existe regra_funcionamento com este id para ser excluido....");
        }
        regra_funcionamentoService.delete(id);
        return regra_funcionamento;
    }
}
