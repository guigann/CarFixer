import { Component, OnInit } from '@angular/core';
import { AlertController, LoadingController, NavController, ToastController } from '@ionic/angular';
import { Agenda } from 'src/app/model/agenda';
import { AgendaService } from 'src/app/services/agenda.service';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-agenda',
  templateUrl: './agenda.page.html',
  styleUrls: ['./agenda.page.scss'],
})

export class AgendaPage implements OnInit {

  agendas: Agenda[];

  constructor(private toastController: ToastController, private navController: NavController, private alertController: AlertController, private agendaService: AgendaService, private loadingController: LoadingController) {
    this.agendas = [];
  }

  ngOnInit() {
    if (UsuarioService.protect()) {
      this.navController.navigateBack('/login');
    }
  }

  async ionViewWillEnter() {
    this.carregarLista();
  }

  async carregarLista() {
    this.showLoader();
    await this.agendaService.get().then((json) => {
      this.agendas = <Agenda[]>(json);
    });
    this.closeLoader();
  }

  showLoader() {
    this.loadingController.create({
      message: 'Carregando...'
    }).then((res) => {
      res.present();
    })
  }

  closeLoader() {
    setTimeout(() => {
      this.loadingController.dismiss().then(() => {
      }).catch((erro) => {
        console.log('Erro: ', erro)
      });
    }, 500);
  }

  async excluir(agenda: Agenda) {
    const alert = await this.alertController.create({
      header: 'Confirma a exclusão?',
      message: "Data:"+ agenda.id_horario+"\t Status:"+ agenda.status,
      
      buttons: [
        {
          text: 'Cancelar'
        }, {
          text: 'Confirmar',
          cssClass: 'danger',
          handler: () => {
            this.agendaService.delete(agenda.id).then(() => {
              this.carregarLista();
              this.showMessage('Registro excluído com sucesso!!!');
            }).catch(() => {
              this.showMessage('Erro ao excluir o resgistro.');
            });
          }
        }
      ]
    });
    await alert.present();
  }
  async showMessage(texto: string) {
    const toast = await this.toastController.create({ message: texto, duration: 1500 });
    toast.present();
  }
}

export { Agenda };
