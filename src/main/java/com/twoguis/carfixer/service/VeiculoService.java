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

    public Veiculo insert(Veiculo veiculo) {
        int id_veiculo = veiculoDao.insert(veiculo);
        veiculo.setId_veiculo(id_veiculo);
        return veiculo;
    }

    public List<Veiculo> get() {
        List<Veiculo> veiculos = veiculoDao.get();

        for (Veiculo veiculo : veiculos) {
            veiculo = getAgendas(veiculo);
        }

        return veiculos;
    }

    public Veiculo getById(int id) {
        Veiculo veiculo = veiculoDao.getById(id);

        veiculo = getAgendas(veiculo);

        return veiculo;
    }

    public Veiculo getByPlaca(String placa){
       return veiculoDao.getByPlaca(placa);
    }

    public void update(Veiculo veiculo) {
        veiculoDao.update(veiculo);
    }

    public void delete(int id) {
        veiculoDao.delete(id);
    }

    public Veiculo getAgendas(Veiculo veiculo) {
        List<Agenda> agendas = agendaDao.getByVeiculo(veiculo.getId_veiculo());
        veiculo.setAgendas(agendas);

        return veiculo;
    }
}
