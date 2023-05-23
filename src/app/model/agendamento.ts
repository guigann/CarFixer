export class Agendamento {
    id: number;
    data: string;
    status: string;
    veiculo_id: number;
    tipo_servico_id: number;

    constructor(){
        this.id = 0;
        this.data = "";
        this.status = "";
        this.veiculo_id = 0;
        this.tipo_servico_id = 0;
    }
}
