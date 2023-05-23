import { Component, OnInit } from '@angular/core';
import { AlertController, ToastController } from '@ionic/angular';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.page.html',
  styleUrls: ['./client-list.page.scss'],
})
export class ClientListPage implements OnInit {

  clienteList: User[];
  userService: UserService;

  constructor(
    private toastController: ToastController,
    private alertControle: AlertController) { 

      this.userService = new UserService();
      this.clienteList = this.userService.getAll();
    }

  ngOnInit() {
  }

  async delete(cliente: User){
    const alert = await this.alertControle.create({
      header: 'Confirm deletion?',
      message: cliente.name,
      buttons: [
        {
          text: 'Cancel'
        }, {
          text: 'Confirm',
          cssClass: 'danger',
          handler: () => {
            this.userService.delete(cliente.id);
            this.showMessage('Deleted successfully!');
            this.clienteList = this.userService.getAll();
          }
        }
      ]
    });
    await alert.present();
  }

  async showMessage(str: string){
    const toast = await this.toastController.create({
      message: str,
      duration: 1500
    })
    toast.present();
  }

}
