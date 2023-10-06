import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Produto } from '../model/produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {
  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  };

  url: string = 'http://localhost:8087/api/v1/produto';
  urlAgenda = (idAgenda: number) =>
    `http://localhost:8087/api/v1/agenda/${idAgenda}/produto`;

  
  constructor(private httpClient: HttpClient) { }

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

  async save(produto: Produto) {
    if (produto.id === 0) {
      return await this.httpClient
        .post(this.url, JSON.stringify(produto), this.httpHeaders)
        .toPromise();
    } else {
      return await this.httpClient
        .put(this.url, JSON.stringify(produto), this.httpHeaders)
        .toPromise();
    }
  }

  async putOnAgenda(idAgenda: number, idProduto: number) {
    return await this.httpClient
      .post(
        this.urlAgenda(idAgenda),
        { id: idProduto, id_agenda: idAgenda, observacao: '' },
        this.httpHeaders
      )
      .toPromise();
  }

  async delete(id: number) {
    let urlAuxiliar = this.url + '/' + id;
    return await this.httpClient.delete(urlAuxiliar).toPromise();
  }

  async deleteFromAgenda(idAgenda: number, idProduto: number) {
    let urlAuxiliar = this.urlAgenda(idAgenda)+"/"+ idProduto;
    return await this.httpClient.delete(urlAuxiliar).toPromise();
  }

  async deleteAllFromAgenda(idAgenda: number) {
    let urlAuxiliar = this.urlAgenda(idAgenda);
    return await this.httpClient.delete(urlAuxiliar).toPromise();
  }
}
