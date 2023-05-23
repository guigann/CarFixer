import { Component, OnInit } from '@angular/core';
import { AlertController, NavController, ToastController } from '@ionic/angular';
import { Veiculo } from 'src/app/model/veiculo';
import { VeiculoService } from 'src/app/services/veiculo.service';

@Component({
  selector: 'app-veiculo',
  templateUrl: './veiculo.page.html',
  styleUrls: ['./veiculo.page.scss'],
})
export class VeiculoPage implements OnInit {
  veiculos: Veiculo[];
  veiculoService: VeiculoService;

  constructor(private toastController: ToastController, private navController: NavController, private alertController: AlertController) {
    this.veiculos = [];
    this.veiculoService = new VeiculoService();
  }

  ngOnInit() { }

  async ionViewWillEnter() {
    this.veiculos = this.veiculoService.getAll();
  }

  async excluir(veiculo: Veiculo) {
    const alert = await this.alertController.create({
      header: 'Confirma a exclusão?',
      message: veiculo.modelo + " - " + veiculo.placa,
      buttons: [{
        text: 'Cancelar'
      }, {
        text: 'Confirmar',
        cssClass: 'danger',
        handler: () => {
          this.veiculoService.delete(veiculo.id);
          this.exibirMensagem('Registro excluído com sucesso!!!');
          this.veiculos = this.veiculoService.getAll();
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

export { Veiculo };

