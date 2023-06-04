package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.HorarioDao;
import com.twoguis.carfixer.model.Horario;

@Service
public class HorarioService {

    private final HorarioDao horarioDao;

    public HorarioService(Jdbi jdbi) {
        this.horarioDao = jdbi.onDemand(HorarioDao.class);
    }

    public Horario inserir(Horario veiculo) {
        int id_horario = horarioDao.insert(veiculo);
        veiculo.setId_horario(id_horario);
        return veiculo;
    }

    public List<Horario> consultarTodos() {
        return horarioDao.getAll();
    }

    public Horario consultarPorId(int id) {
        return horarioDao.get(id);
    }

    public void alterar(Horario veiculo) {
        horarioDao.update(veiculo);
    }

    public void excluir(int id) {
        horarioDao.delete(id);
    }

}
