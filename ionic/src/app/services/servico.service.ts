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

  constructor(private httpClient: HttpClient) {}

  async get() {
    return await this.httpClient.get(this.url).toPromise();
  }

  async getById(id: number) {
    let urlAuxiliar = this.url + '/' + id;
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

  async delete(id: number) {
    let urlAuxiliar = this.url + '/' + id;
    return await this.httpClient.delete(urlAuxiliar).toPromise();
  }

}
