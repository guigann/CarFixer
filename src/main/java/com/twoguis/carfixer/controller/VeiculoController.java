package com.twoguis.carfixer.controller;

import java.util.List;

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


@RestController
@RequestMapping("/api/v1/veiculo")
public class VeiculoController {
    private final VeiculoService veiculoService;
    
    public VeiculoController(VeiculoService veiculoService){
        this.veiculoService = veiculoService;
    }
    
    @GetMapping({"/", ""})
    public List<Veiculo> consultarTodos(){
        List<Veiculo> veiculoList = veiculoService.consultarTodos();
        return veiculoList;
    }
    
    @GetMapping("/{id}")
    public Veiculo consultarVeiculo(@PathVariable("id") int id){
        Veiculo ret = veiculoService.consultarPorId(id);
        return ret;
    }
    
    @PostMapping({"", "/"})
    public Veiculo inserir(@RequestBody Veiculo veiculo){
        Veiculo ret = veiculoService.inserir(veiculo);
        return ret;
    }
    
    @PutMapping({"", "/"})
    public Veiculo alterar(@RequestBody Veiculo veiculo){
        veiculoService.alterar(veiculo);
        return veiculo;
    }
    
    @DeleteMapping("/{id}")
    public Veiculo excluir(@PathVariable("id") int id){
        Veiculo veiculo = veiculoService.consultarPorId(id);
        if (veiculo == null){
            throw new RuntimeException("Nao existe veiculo com este id para ser excluido....");
        }
        veiculoService.excluir(id);
        return veiculo;
    }

    @GetMapping("/placa/{placa}/exists")
    public int placaExists(@PathVariable("placa") String placa) {
        Veiculo veiculo = veiculoService.getByPlaca(placa);
        
        if (veiculo != null && veiculo.getPlaca().equals(placa)){
            return 200;
        } else
            return 404;
    }
}
