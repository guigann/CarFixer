import { Agenda } from "./agenda";

export class Veiculo {
    id: number;
    placa: string;
    modelo: string;
    tipo: string;
    id_cliente: number;
    agendas: Agenda[]

    constructor(){
        this.id = 0;
        this.placa = "";
        this.modelo = "";
        this.tipo = "";
        this.id_cliente = 0;
        this.agendas = [];
    }
}
