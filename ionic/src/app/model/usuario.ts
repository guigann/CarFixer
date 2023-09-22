import { Veiculo } from "./veiculo";

export class Usuario {
    id: number;
    nome: string;
    cpf: string;
    telefone: string;
    email: string;
    senha: string;
    permission: "Cliente"|"Funcionario"|"Admin";
    veiculos: Veiculo[]

    constructor() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.email = "";
        this.telefone = "";
        this.senha = "";
        this.permission = "Cliente";
        this.veiculos = [];
    }
}
