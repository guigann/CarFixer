// import { Injectable } from '@angular/core';
// import { Veiculo } from '../model/veiculo';

// @Injectable({
//   providedIn: 'root'
// })
// export class VeiculoService {

//   constructor() { }

//   save(veiculo: Veiculo) {
//     let veiculos = this.getAll();

//     if (veiculo.id === 0) {
//       veiculo.id = (new Date().getTime() / 1000) * Math.random();
//       veiculos.push(veiculo);
//     } else {
//       let posicao = veiculos.findIndex((elemento: Veiculo) => elemento.id === veiculo.id);
//       veiculos.splice(posicao, 1, veiculo);
//     }

//     localStorage.setItem('veiculos', JSON.stringify(veiculos));
//   }

//   getAll() {
//     return JSON.parse(localStorage.getItem('veiculos') || '[]');
//   }

//   getById(id: number) {
//     let veiculos = this.getAll();
//     let veiculo = new Veiculo();
//     veiculo = veiculos.find((elemento: Veiculo) => elemento.id === id);
//     return veiculo;
//   }

//   delete(id: number) {
//     let veiculos = this.getAll();
//     veiculos = veiculos.filter((elemento: Veiculo) => elemento.id !== id);
//     localStorage.setItem('veiculos', JSON.stringify(veiculos));
//   }

//   checkPlaca(veiculo: Veiculo) {
//     let veiculos = this.getAll();
//     if (veiculos.length !== 0) {
//       for (let i = 0; i < veiculos.length; i++) {
//         if (veiculos[i] === veiculo && veiculo.placa === veiculos[i].placa) {
//           return false;
//         }
//       }
//       return true;
//     }
//     return true;
//   }
// }

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Veiculo } from '../model/veiculo';

@Injectable({
  providedIn: 'root'
})
export class VeiculoService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  url: string = 'http://localhost:8087/api/v1/veiculo';

  constructor(private httpClient: HttpClient) { }

  async get() {
    return await this.httpClient.get(this.url).toPromise();
  }

  async getById(id: number) {
    let urlAuxiliar = this.url + "/" + id;
    return await this.httpClient.get(urlAuxiliar).toPromise();
  }

  async save(veiculo: Veiculo) {
    if (veiculo.id === 0) {
      return await this.httpClient.post(this.url, JSON.stringify(veiculo), this.httpHeaders).toPromise();
    } else {
      return await this.httpClient.put(this.url, JSON.stringify(veiculo), this.httpHeaders).toPromise();
    }
  }

  async delete(id: number) {
    let urlAuxiliar = this.url + "/" + id;
    return await this.httpClient.delete(urlAuxiliar).toPromise();
  }

  async checkPlaca(placa: string) {
    let urlAuxiliar = this.url + "/placa/" + placa + '/exists';
    return await this.httpClient.get(urlAuxiliar).toPromise();
  }
}