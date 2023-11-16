import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NavController, ToastController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ActivatedRoute } from '@angular/router';
import { MaskitoElementPredicateAsync, MaskitoOptions } from '@maskito/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss']
})
export class ProfilePage implements OnInit {
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


    this.usuarioService.getById(UsuarioService.getLogin().id).then((json: any) => {
      this.usuario = <Usuario>(json);
      this.usuario.cpf = this.usuario.cpf.replace(/^(\d{3})(\d{3})(\d{3})(\d{2})$/, '$1.$2.$3-$4');
      this.usuario.telefone = this.usuario.telefone.replace(/^(\d{2})(\d{1})(\d{4})(\d{4})$/, '($1) $2 $3-$4');

      this.formGroup.get('nome')?.setValue(this.usuario.nome);
      this.formGroup.get('email')?.setValue(this.usuario.email);
      this.formGroup.get('cpf')?.setValue(this.usuario.cpf);
      this.formGroup.get('telefone')?.setValue(this.usuario.telefone);
    });


    // this.usuario = UsuarioService.getLogin();
  }

  ngOnInit() {
    if (UsuarioService.protect()) {
      this.navController.navigateBack('/login');
    }
  }

  save() {
    console.log(this.usuario.veiculos.length)
    this.usuario.nome = this.formGroup.value.nome;
    this.usuario.email = this.formGroup.value.email;
    this.usuario.cpf = this.formGroup.value.cpf.replace(/[.-]/g, '');
    this.usuario.telefone = this.formGroup.value.telefone.replace(/[\s()-]/g, '');

    this.usuarioService.save(this.usuario)
      .then((json: any) => {
        this.usuario = <Usuario>(json);
        if (this.usuario) {
          this.usuarioService.setLogin(this.usuario);
          this.exibirMensagem('Dados salvos com sucesso!!!');
          this.navController.navigateBack('/home');
        } else {
          this.exibirMensagem('Erro ao salvar os dados!');
        }
      }).catch((erro: any) => {
        this.exibirMensagem('Erro ao salvar os dados! Erro:' + erro['mensage']);
      })

  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }

  readonly cpfMask: MaskitoOptions = {
    mask: [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/],
  };

  readonly phoneMask: MaskitoOptions = {
    mask: ['(', /\d/, /\d/, ')', ' ', /\d/, ' ', /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/],
  };

  readonly maskPredicate: MaskitoElementPredicateAsync = async (el) => (el as HTMLIonInputElement).getInputElement();

}
