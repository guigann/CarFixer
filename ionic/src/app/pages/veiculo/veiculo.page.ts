import { Component, OnInit } from '@angular/core';
import { AlertController, LoadingController, NavController, ToastController } from '@ionic/angular';
import { Veiculo } from 'src/app/model/veiculo';
import { UsuarioService } from 'src/app/services/usuario.service';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { Usuario } from '../cliente/cliente.page';

@Component({
  selector: 'app-veiculo',
  templateUrl: './veiculo.page.html',
  styleUrls: ['./veiculo.page.scss'],
})

export class VeiculoPage implements OnInit {
  veiculos: Veiculo[];
  usuarios: Usuario[];

  constructor(private toastController: ToastController, private navController: NavController, private alertController: AlertController, private veiculoService: VeiculoService, private usuarioService: UsuarioService, private loadingController: LoadingController) {
    this.veiculos = [];
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
    await this.veiculoService.get().then((json) => {
      this.veiculos = <Veiculo[]>(json);
    });


    this.usuarioService.get().then((json) => {
      this.usuarios = (<Usuario[]>json)
    });


    this.closeLoader();
  }

  getNome(id_cliente: number) {
    let result = '';
    this.usuarios.map((usuario) => {
      if (usuario.id === id_cliente) {
        result = usuario.nome + " - " + usuario.cpf;
      }
    })
    return result;
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

  async excluir(veiculo: Veiculo) {
    const alert = await this.alertController.create({
      header: 'Confirma a exclusão?',
      message: veiculo.modelo + "(" + veiculo.placa + ")",
      buttons: [
        {
          text: 'Cancelar'
        }, {
          text: 'Confirmar',
          cssClass: 'danger',
          handler: () => {
            this.veiculoService.delete(veiculo.id).then(() => {
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

export { Veiculo };

