export class User {
    id: number;
    name: string;
    cpf: string;
    phone: string;
    email: string;
    password: string;
    permission: number;

    constructor() {
        this.id = 0;
        this.name = "";
        this.cpf = "";
        this.email = "";
        this.phone = "";
        this.password = "";
        this.permission = 0;
    }
}
