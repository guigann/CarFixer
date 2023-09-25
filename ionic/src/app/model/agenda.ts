import { Produto } from "./produto";

export class Agenda {
  id: number;
  id_horario: number;
  id_veiculo: number;
  status: Status;
  dt_previsao: Date | undefined;
  dt_fim: Date | undefined;
  observacao: string;
  produtos: Produto[];

  constructor() {
    this.id = 0;
    this.id_horario = 0;
    this.id_veiculo = 0;
    this.status = Status.Pendente;
    this.dt_previsao = undefined;
    this.dt_fim = undefined;
    this.observacao = '';
    this.produtos = [];
  }
}

export enum Status {
  Pendente = "Pendente",
  Aprovado = "Aprovado",
  Reprovado = "Reprovado",
  Em_Andamento = "Em_Andamento",
  Concluido = "Concluido",
  Cancelado = "Cancelado"
}