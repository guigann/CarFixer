import { Component, OnInit } from '@angular/core';
import { UsuarioService } from 'src/app/services/usuario.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NavController, ToastController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';

@Component({
  selector: 'app-add-cliente',
  templateUrl: './add-cliente.page.html',
  styleUrls: ['./add-cliente.page.scss'],
})
export class AddClientePage implements OnInit {
  titlePage: string;
  usuario: Usuario;
  formGroup: FormGroup;

  constructor(private activatedRoute: ActivatedRoute, private toastController: ToastController, private navController: NavController, private formBuilder: FormBuilder, private usuarioService: UsuarioService) {

    this.usuario = new Usuario();

    this.formGroup = this.formBuilder.group({
      'nome': [this.usuario?.nome, Validators.compose([
        Validators.required
      ])],
      'cpf': [this.usuario?.cpf, Validators.compose([
        Validators.required
      ])],
      'email': [this.usuario?.email, Validators.compose([
        Validators.required
      ])],
      'telefone': [this.usuario?.telefone, Validators.compose([
        Validators.required
      ])]
    });



    let id = this.activatedRoute.snapshot.params['id'];

    if (id != null) {
      this.titlePage = "Cliente";
      this.usuarioService.getById(id).then((json) => {
        this.usuario = <Usuario>(json);
        this.formGroup.get('nome')?.setValue(this.usuario.nome);
        this.formGroup.get('email')?.setValue(this.usuario.email);
        this.formGroup.get('cpf')?.setValue(this.usuario.cpf);
        this.formGroup.get('telefone')?.setValue(this.usuario.telefone);
      });
    } else {
      this.titlePage = "Cadastro de Cliente"
    }

    // if (id > 0) {
    //   this.usuarioService.getById(id).then((json) => {
    //     this.usuario = <Usuario>(json);
    //   });

    //   if (this.usuario)
    //     this.navController.navigateBack('/cliente');
    //   this.titlePage = "Cliente"
    // } else {
    //   this.titlePage = "Cadastro de Cliente"

    // }
  }

  ngOnInit() {
  }

  bt_save() {
    this.usuario.nome = this.formGroup.value.nome;
    this.usuario.email = this.formGroup.value.email;
    this.usuario.cpf = this.formGroup.value.cpf;
    this.usuario.telefone = this.formGroup.value.telefone;
    this.usuario.senha = "0";
    this.usuario.permission = 0;

    this.usuarioService.checkEmail(this.usuario.email).then((json) => {
      let result = <number>(json);
      if (result === 404) {

        this.usuarioService.save(this.usuario)
          .then((json: any) => {
            this.usuario = <Usuario>(json);
            if (this.usuario) {
              this.showMessage('Registro salvo com sucesso!!!');
              this.navController.navigateBack('/usuario');
            } else {
              this.showMessage('Erro ao salvar o registro!');
            }
          }).catch((erro: any) => {
            this.showMessage('Erro ao salvar o registro! Erro:' + erro['mensage']);
          })

      } else {
        this.showMessage('Erro ao salvar o registro! Numero já cadastrado!');
      }
    }).catch((erro: any) => {
      this.showMessage('Erro ao verificar o email! Erro:' + erro['mensage']);
    });

  }

  async showMessage(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }
}