import { Injectable } from '@angular/core';
import { Agendamento } from '../model/agendamento';

@Injectable({
  providedIn: 'root'
})
export class AgendamentoService {

  constructor() { }

  save(agendamento: Agendamento) {
    let agendamentos = this.getAll();

    if (agendamento.id === 0) {
      agendamento.id = (new Date().getTime() / 1000) * Math.random();
      agendamentos.push(agendamento);
    } else {
      let posicao = agendamentos.findIndex((elemento: Agendamento) => elemento.id === agendamento.id);
      agendamentos.splice(posicao, 1, agendamento);
    }

    localStorage.setItem('agendamentos', JSON.stringify(agendamentos));
  }

  getAll() {
    return JSON.parse(localStorage.getItem('agendamentos') || '[]');
  }

  getById(id: number) {
    let agendamentos = this.getAll();
    let agendamento = new Agendamento();
    agendamento = agendamentos.find((elemento: Agendamento) => elemento.id === id);
    return agendamento;
  }

  delete(id: number) {
    let agendamentos = this.getAll();
    agendamentos = agendamentos.filter((elemento: Agendamento) => elemento.id !== id);
    localStorage.setItem('agendamentos', JSON.stringify(agendamentos));
  }
}