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

    public Horario insert(Horario veiculo) {
        int id_horario = horarioDao.insert(veiculo);
        veiculo.setId(id_horario);
        return veiculo;
    }

    public List<Horario> get() {
        return horarioDao.get();
    }

    public Horario getById(int id) {
        return horarioDao.getById(id);
    }

    public void update(Horario veiculo) {
        horarioDao.update(veiculo);
    }

    public void delete(int id) {
        horarioDao.delete(id);
    }

}
