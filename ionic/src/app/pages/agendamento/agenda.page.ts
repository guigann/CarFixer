import { Component, OnInit } from '@angular/core';
import { AlertController, NavController, ToastController } from '@ionic/angular';
import { Agenda } from 'src/app/model/agenda';
import { AgendaService } from 'src/app/services/agenda.service';

@Component({
  selector: 'app-agenda',
  templateUrl: './agenda.page.html',
  styleUrls: ['./agenda.page.scss'],
})
export class AgendaPage implements OnInit {
  agendas: Agenda[];
  agendaService: AgendaService;

  constructor(private toastController: ToastController, private navController: NavController, private alertController: AlertController) {
    this.agendas = [];
    this.agendaService = new AgendaService();
  }

  ngOnInit() { }

  async ionViewWillEnter() {
    this.agendas = this.agendaService.getAll();
  }

  async excluir(agenda: Agenda) {
    const alert = await this.alertController.create({
      header: 'Confirma a exclusão?',
      message: agenda.id_horario + " - " + agenda.id_veiculo,   //mudar id_horario p/ obj. horario
      buttons: [{
        text: 'Cancelar'
      }, {
        text: 'Confirmar',
        cssClass: 'danger',
        handler: () => {
          this.agendaService.delete(agenda.id);
          this.exibirMensagem('Registro excluído com sucesso!!!');
          this.agendas = this.agendaService.getAll();
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

export { Agenda };
