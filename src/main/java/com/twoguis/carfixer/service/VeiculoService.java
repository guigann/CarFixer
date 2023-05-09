package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.VeiculoDao;
import com.twoguis.carfixer.model.Veiculo;

@Service
public class VeiculoService {
    
    private final VeiculoDao veiculoDao;
    
    public VeiculoService(Jdbi jdbi){
        this.veiculoDao = jdbi.onDemand(VeiculoDao.class);
    }
    
    public Veiculo inserir (Veiculo veiculo){
        int id_veiculo = veiculoDao.insert(veiculo);
        veiculo.setId_veiculo(id_veiculo);
        return veiculo;
    }
    
    public List<Veiculo> consultarTodos(){
        return veiculoDao.getAll();
    }

    public Veiculo consultarPorId(int id){
        return veiculoDao.get(id);
    }
    
    public void alterar(Veiculo veiculo){
        veiculoDao.update(veiculo);
    }
    
    public void excluir(int id){
        veiculoDao.delete(id);
    }
    
}
