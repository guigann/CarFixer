import { Injectable } from '@angular/core';
import { Agenda } from '../model/agenda';

@Injectable({
  providedIn: 'root'
})
export class AgendaService {

  constructor() { }

  save(agenda: Agenda) {
    let agendas = this.getAll();

    if (agenda.id === 0) {
      agenda.id = (new Date().getTime() / 1000) * Math.random();
      agendas.push(agenda);
    } else {
      let posicao = agendas.findIndex((elemento: Agenda) => elemento.id === agenda.id);
      agendas.splice(posicao, 1, agenda);
    }

    localStorage.setItem('agendas', JSON.stringify(agendas));
  }

  getAll() {
    return JSON.parse(localStorage.getItem('agendas') || '[]');
  }

  getById(id: number) {
    let agendas = this.getAll();
    let agenda = new Agenda();
    agenda = agendas.find((elemento: Agenda) => elemento.id === id);
    return agenda;
  }

  delete(id: number) {
    let agendas = this.getAll();
    agendas = agendas.filter((elemento: Agenda) => elemento.id !== id);
    localStorage.setItem('agendas', JSON.stringify(agendas));
  }
}