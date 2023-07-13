package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.AgendaDao;
import com.twoguis.carfixer.dao.VeiculoDao;
import com.twoguis.carfixer.model.Agenda;
import com.twoguis.carfixer.model.Veiculo;

@Service
public class VeiculoService {

    private final VeiculoDao veiculoDao;
    private final AgendaDao agendaDao;

    public VeiculoService(Jdbi jdbi) {
        this.veiculoDao = jdbi.onDemand(VeiculoDao.class);
        this.agendaDao = jdbi.onDemand(AgendaDao.class);
    }

    public Veiculo inserir(Veiculo veiculo) {
        int id_veiculo = veiculoDao.insert(veiculo);
        veiculo.setId_veiculo(id_veiculo);
        return veiculo;
    }

    public List<Veiculo> consultarTodos() {
        List<Veiculo> veiculos = veiculoDao.getAll();

        for (Veiculo veiculo : veiculos) {
            veiculo = listarAgendas(veiculo);
        }

        return veiculos;
    }

    public Veiculo consultarPorId(int id) {
        Veiculo veiculo = veiculoDao.get(id);

        veiculo = listarAgendas(veiculo);

        return veiculo;
    }

    public Veiculo getByPlaca(String placa){
       return veiculoDao.getByPlaca(placa);
    }

    public void alterar(Veiculo veiculo) {
        veiculoDao.update(veiculo);
    }

    public void excluir(int id) {
        veiculoDao.delete(id);
    }

    public Veiculo listarAgendas(Veiculo veiculo) {
        List<Agenda> agendas = agendaDao.getAllByVeiculo(veiculo.getId_veiculo());
        veiculo.setAgendas(agendas);

        return veiculo;
    }
}
