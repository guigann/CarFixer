import { Component, OnInit } from '@angular/core';
import { AlertController, NavController, ToastController } from '@ionic/angular';
import { Agendamento } from 'src/app/model/agendamento';
import { AgendamentoService } from 'src/app/services/agendamento.service';

@Component({
  selector: 'app-agendamento',
  templateUrl: './agendamento.page.html',
  styleUrls: ['./agendamento.page.scss'],
})
export class AgendamentoPage implements OnInit {
  agendamentos: Agendamento[];
  agendamentoService: AgendamentoService;

  constructor(private toastController: ToastController, private navController: NavController, private alertController: AlertController) {
    this.agendamentos = [];
    this.agendamentoService = new AgendamentoService();
  }

  ngOnInit() { }

  async ionViewWillEnter() {
    this.agendamentos = this.agendamentoService.getAll();
  }

  async excluir(agendamento: Agendamento) {
    const alert = await this.alertController.create({
      header: 'Confirma a exclusão?',
      message: agendamento.data + " - " + agendamento.veiculo_id,
      buttons: [{
        text: 'Cancelar'
      }, {
        text: 'Confirmar',
        cssClass: 'danger',
        handler: () => {
          this.agendamentoService.delete(agendamento.id);
          this.exibirMensagem('Registro excluído com sucesso!!!');
          this.agendamentos = this.agendamentoService.getAll();
        }
      }
      ]
    });
    await alert.present();
  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({ message: texto, duration: 1500 });
    toast.present();
  }
}

export { Agendamento };
