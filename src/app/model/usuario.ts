import { Veiculo } from "./veiculo";

export class Usuario {
    id: number;
    nome: string;
    cpf: string;
    telefone: string;
    email: string;
    senha: string;
    permission: number; //create a Enum class
    veiculos: Veiculo[]

    constructor() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.email = "";
        this.telefone = "";
        this.senha = "";
        this.permission = 0;
        this.veiculos = [];
    }
}
