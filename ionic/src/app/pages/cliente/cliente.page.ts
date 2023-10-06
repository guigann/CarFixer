import { Component, OnInit } from '@angular/core';
import { AlertController, LoadingController, NavController, ToastController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.page.html',
  styleUrls: ['./cliente.page.scss'],
})

export class ClientePage implements OnInit {
  usuarios: Usuario[];

  constructor(private toastController: ToastController, private navController: NavController, private alertController: AlertController, private usuarioService: UsuarioService, private loadingController: LoadingController) {
    this.usuarios = [];
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
    await this.usuarioService.get().then((json) => {
      this.usuarios = <Usuario[]>(json);
      this.usuarios = this.usuarios.filter(user => user.permission === "Cliente")
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

  async excluir(usuario: Usuario) {
    const alert = await this.alertController.create({
      header: 'Confirma a exclusão?',
      message: usuario.nome,
      buttons: [
        {
          text: 'Cancelar'
        }, {
          text: 'Confirmar',
          cssClass: 'danger',
          handler: () => {
            this.usuarioService.delete(usuario.id).then(() => {
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

export { Usuario };

