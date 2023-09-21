import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Servico } from '../model/servico';

@Injectable({
  providedIn: 'root',
})
export class ServicoService {
  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  url: string = 'http://localhost:8087/api/v1/servico';
  urlAgenda = (idAgenda: number) =>
    `http://localhost:8087/api/v1/agenda/${idAgenda}/servico`;

  constructor(private httpClient: HttpClient) {}

  async get() {
    return await this.httpClient.get(this.url).toPromise();
  }

  async getById(id: number) {
    let urlAuxiliar = this.url + '/' + id;
    return await this.httpClient.get(urlAuxiliar).toPromise();
  }

  async getByIdAgenda(idAgenda: number) {
    let urlAuxiliar = this.urlAgenda(idAgenda);
    return await this.httpClient.get(urlAuxiliar).toPromise();
  }

  async save(servico: Servico) {
    if (servico.id === 0) {
      return await this.httpClient
        .post(this.url, JSON.stringify(servico), this.httpHeaders)
        .toPromise();
    } else {
      return await this.httpClient
        .put(this.url, JSON.stringify(servico), this.httpHeaders)
        .toPromise();
    }
  }
  async putOnAgenda(idAgenda: number, idServico: number) {
    return await this.httpClient
      .post(
        this.urlAgenda(idAgenda),
        { id: idServico, id_agenda: idAgenda, observacao: '' },
        this.httpHeaders
      )
      .toPromise();
  }

  async delete(id: number) {
    let urlAuxiliar = this.url + '/' + id;
    return await this.httpClient.delete(urlAuxiliar).toPromise();
  }

  async deleteFromAgenda(idAgenda: number, idServico: number) {
    let urlAuxiliar = this.urlAgenda(idAgenda)+"/"+ idServico;
    return await this.httpClient.delete(urlAuxiliar).toPromise();
  }

  async deleteAllFromAgenda(idAgenda: number) {
    let urlAuxiliar = this.urlAgenda(idAgenda);
    return await this.httpClient.delete(urlAuxiliar).toPromise();
  }
}

//   GET http://localhost:8087/api/v1/agenda/10/servico
//   POST http://localhost:8087/api/v1/agenda/2/servico
// DELETE http://localhost:8087/api/v1/agenda/2/servico/1
// DELETE ALL http://localhost:8087/api/v1/agenda/10/servico/
