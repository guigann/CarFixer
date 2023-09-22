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

    public Horario insert(Horario horario) {
        int id_horario = horarioDao.insert(horario);
        horario.setId(id_horario);
        return horario;
    }

    public List<Horario> get() {
        return horarioDao.get();
    }

    public Horario getById(int id) {
        return horarioDao.getById(id);
    }

    public void update(Horario horario) {
        horarioDao.update(horario);
    }

    public void delete(int id) {
        horarioDao.delete(id);
    }

}
