import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Horario } from '../model/horario';

@Injectable({
  providedIn: 'root'
})
export class HorarioService {
  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  url: string = 'http://localhost:8087/api/v1/horario';

  
  constructor(private httpClient: HttpClient) { }

  async get() {
    return await this.httpClient.get(this.url).toPromise();
  }

  async getById(id: number) {
    let urlAuxiliar = this.url + '/' + id;
    return await this.httpClient.get(urlAuxiliar).toPromise();
  }

  async save(horario: Horario) {
    if (horario.id === 0) {
      return await this.httpClient
        .post(this.url, JSON.stringify(horario), this.httpHeaders)
        .toPromise();
    } else {
      return await this.httpClient
        .put(this.url, JSON.stringify(horario), this.httpHeaders)
        .toPromise();
    }
  }

  async delete(id: number) {
    let urlAuxiliar = this.url + '/' + id;
    return await this.httpClient.delete(urlAuxiliar).toPromise();
  }

}
