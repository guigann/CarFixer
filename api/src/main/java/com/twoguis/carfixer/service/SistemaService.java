package com.twoguis.carfixer.service;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.twoguis.carfixer.dao.SistemaDao;
import com.twoguis.carfixer.model.Sistema;

@Service
public class SistemaService {

    private final SistemaDao sistemaDao;

    public SistemaService(Jdbi jdbi) {
        this.sistemaDao = jdbi.onDemand(SistemaDao.class);
    }

    public Sistema insert(Sistema sistema) {
        int id_sistema = sistemaDao.insert(sistema);
        sistema.setId(id_sistema);
        return sistema;
    }

    public List<Sistema> get() {
        return sistemaDao.get();
    }

    public Sistema getById(int id) {
        return sistemaDao.getById(id);
    }

    // public Sistema getByCpf(String cpf) {
    // return sistemaDao.getByCpf(cpf);
    // }

    // public Sistema getByEmail(String email) {
    // return sistemaDao.getByEmail(email);
    // }

    public void update(Sistema sistema) {
        sistemaDao.update(sistema);
    }

    public void delete(int id) {
        sistemaDao.delete(id);
    }
}
