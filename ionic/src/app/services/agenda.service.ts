import { Injectable } from '@angular/core';
import { Agenda } from '../model/agenda';
import { HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AgendaService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  url: string = 'http://localhost:8087/api/v1/agenda';

  constructor(private httpClient: HttpClient) { }

  async get() {
    return await this.httpClient.get(this.url).toPromise();
  }

  async getById(id: number) {
    let urlAuxiliar = this.url + "/" + id;
    return await this.httpClient.get(urlAuxiliar).toPromise();
  }

  async getByUser(id: number) {
    let urlAuxiliar = this.url + "/usuario/" + id;
    return await this.httpClient.get(urlAuxiliar).toPromise();
  }

  async save(agenda: Agenda) {
    if (agenda.id === 0) {
      return await this.httpClient.post(this.url, JSON.stringify(agenda), this.httpHeaders).toPromise();
    } else {
      return await this.httpClient.put(this.url, JSON.stringify(agenda), this.httpHeaders).toPromise();
    }
  }

  async delete(id: number) {
    let urlAuxiliar = this.url + "/" + id;
    return await this.httpClient.delete(urlAuxiliar).toPromise();
  }
//   constructor() { }

//   save(agenda: Agenda) {
//     let agendas = this.getAll();

//     if (agenda.id === 0) {
//       agenda.id = (new Date().getTime() / 1000) * Math.random();
//       agendas.push(agenda);
//     } else {
//       let posicao = agendas.findIndex((elemento: Agenda) => elemento.id === agenda.id);
//       agendas.splice(posicao, 1, agenda);
//     }

//     localStorage.setItem('agendas', JSON.stringify(agendas));
//   }

//   getAll() {
//     return JSON.parse(localStorage.getItem('agendas') || '[]');
//   }

//   getById(id: number) {
//     let agendas = this.getAll();
//     let agenda = new Agenda();
//     agenda = agendas.find((elemento: Agenda) => elemento.id === id);
//     return agenda;
//   }

//   delete(id: number) {
//     let agendas = this.getAll();
//     agendas = agendas.filter((elemento: Agenda) => elemento.id !== id);
//     localStorage.setItem('agendas', JSON.stringify(agendas));
//   }
}