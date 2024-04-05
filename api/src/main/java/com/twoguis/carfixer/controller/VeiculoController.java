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

import com.twoguis.carfixer.model.Veiculo;
import com.twoguis.carfixer.service.VeiculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
// @RequestMapping("/api/v1/veiculo")
@RequestMapping(value = "/api/v1/veiculo", produces = {"application/json"})
@Tag(name = "veiculo")
@CrossOrigin("*")
public class VeiculoController {
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @Operation(summary = "Consultar todos os veículos", method = "GET")
    @GetMapping({ "/", "" })
    public List<Veiculo> get() {
        List<Veiculo> veiculoList = veiculoService.get();
        return veiculoList;
    }

    @Operation(summary = "Consultar veículo por id", method = "GET")
    @GetMapping("/{id}")
    public Veiculo consultarVeiculo(@PathVariable("id") int id) {
        Veiculo ret = veiculoService.getById(id);
        return ret;
    }

    @Operation(summary = "Consultar veículo por id do usuário (dono do veículo)", method = "GET")
    @GetMapping("/usuario/{id}")
    public List<Veiculo> consultarPorUsuario(@PathVariable("id") int id) {
        List<Veiculo> ret = veiculoService.getByUsuario(id);
        return ret;
    }

    @Operation(summary = "Inserir veículo", method = "POST")
    @PostMapping({ "", "/" })
    public Veiculo insert(@RequestBody Veiculo veiculo) {
        Veiculo ret = veiculoService.insert(veiculo);
        return ret;
    }

    @Operation(summary = "Alterar veículo", method = "PUT")
    @PutMapping({ "", "/" })
    public Veiculo update(@RequestBody Veiculo veiculo) {
        veiculoService.update(veiculo);
        return veiculo;
    }

    @Operation(summary = "Deletar veículo por id", method = "DELETE")
    @DeleteMapping("/{id}")
    public Veiculo delete(@PathVariable("id") int id) {
        Veiculo veiculo = veiculoService.getById(id);
        if (veiculo == null) {
            throw new RuntimeException("Nao existe veiculo com este id para ser excluido....");
        }
        veiculoService.delete(id);
        return veiculo;
    }

    @Operation(summary = "Verificar se uma placa está cadastrada", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Placa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Placa não encontrada"),
    })
    @GetMapping("/placa/{placa}/exists")
    public int placaExists(@PathVariable("placa") String placa) {
        Veiculo veiculo = veiculoService.getByPlaca(placa);

        if (veiculo != null && veiculo.getPlaca().equals(placa)) {
            return 200;
        } else
            return 404;
    }
}
