import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertController, LoadingController, NavController, ToastController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  usuario: Usuario;
  formGroup: FormGroup;

  constructor(private toastController: ToastController, private navController: NavController, private formBuilder: FormBuilder, private alertController: AlertController, private usuarioService: UsuarioService, private loadingController: LoadingController) {
    this.usuario = new Usuario();

    this.formGroup = this.formBuilder.group({
      'email': [this.usuario.email, Validators.compose([
        Validators.required
      ])],
      'senha': [this.usuario.senha, Validators.compose([
        Validators.required
      ])]
    })
  }

  ngOnInit() {
    if (!UsuarioService.protect()) {
      this.navController.navigateBack('/home');
    }
  }

  bt_login() {
    this.showLoader();
    this.usuario.email = this.formGroup.value.email;
    this.usuario.senha = this.formGroup.value.senha;

    this.usuarioService.login(this.usuario).then((json: any) => {
      let result = <number>(json);
      if (result === 200) {
        this.usuarioService.getByEmail(this.usuario.email).then((json: any) => {
          this.usuario = <Usuario>(json);
          if (this.usuario) {
            this.usuarioService.setLogin(this.usuario);
            this.showMessage('Login realizado com sucesso!!!');
            this.navController.navigateBack('/home');
          } else {
            this.showMessage("There was an error in login");
          }
        }).catch((erro: any) => {
          this.showMessage('There was an error in login! Erro:' + erro['mensage']);
        });
      } else {
        this.showMessage('Email ou senha incorretos!')
      }
    }).catch((erro) => {
      this.showMessage('There was an error in login! Erro:' + erro['mensage']);
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

  async showMessage(str: string) {
    const toast = await this.toastController.create({
      message: str,
      duration: 1500
    })
    toast.present();
  }

}
