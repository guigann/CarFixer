export class Veiculo {
    id: number;
    placa: string;
    modelo: string;
    tipo: string;
    user_id: number;

    constructor(){
        this.id = 0;
        this.placa = "";
        this.modelo = "";
        this.tipo = "";
        this.user_id = 0;
    }
}
