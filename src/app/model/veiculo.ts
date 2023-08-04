export class Veiculo {
    id: number;
    placa: string;
    modelo: string;
    tipo: string;
    id_cliente: number;

    constructor(){
        this.id = 0;
        this.placa = "";
        this.modelo = "";
        this.tipo = "";
        this.id_cliente = 0;
    }
}
