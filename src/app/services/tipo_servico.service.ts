import { Injectable } from '@angular/core';
import { Tipo_Servico } from '../model/tipo_servico';

@Injectable({
  providedIn: 'root'
})
export class Tipo_ServicoService {

  constructor() {
    // criar tipo_servicos para poder testar
    let tipo_servicos = [
      {
        id: 1,
        nome: "Revisão",
        imagem: "default",
        descricao: "Revisão"
      },
      {
        id: 2,
        nome: "Funelaria",
        imagem: "default",
        descricao: "Funelaria"
      }];
      localStorage.setItem('tipo_servicos', JSON.stringify(tipo_servicos));
  }


  save(tipo_servico: Tipo_Servico) {
    let tipo_servicos = this.getAll();

    if (tipo_servico.id === 0) {
      tipo_servico.id = (new Date().getTime() / 1000) * Math.random();
      tipo_servicos.push(tipo_servico);
    } else {
      let posicao = tipo_servicos.findIndex((elemento: Tipo_Servico) => elemento.id === tipo_servico.id);
      tipo_servicos.splice(posicao, 1, tipo_servico);
    }

    localStorage.setItem('tipo_servicos', JSON.stringify(tipo_servicos));
  }

  getAll() {
    return JSON.parse(localStorage.getItem('tipo_servicos') || '[]');
  }

  getById(id: number) {
    let tipo_servicos = this.getAll();
    let tipo_servico = new Tipo_Servico();
    tipo_servico = tipo_servicos.find((elemento: Tipo_Servico) => elemento.id === id);
    return tipo_servico;
  }

  delete(id: number) {
    let tipo_servicos = this.getAll();
    tipo_servicos = tipo_servicos.filter((elemento: Tipo_Servico) => elemento.id !== id);
    localStorage.setItem('tipo_servicos', JSON.stringify(tipo_servicos));
  }
}