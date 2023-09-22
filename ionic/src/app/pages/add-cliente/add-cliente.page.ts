import { Component, OnInit } from '@angular/core';
import { UsuarioService } from 'src/app/services/usuario.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NavController, ToastController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';
import { Veiculo } from '../veiculo/veiculo.page';
import { VeiculoService } from 'src/app/services/veiculo.service';

@Component({
  selector: 'app-add-cliente',
  templateUrl: './add-cliente.page.html',
  styleUrls: ['./add-cliente.page.scss'],
})
export class AddClientePage implements OnInit {
  titlePage: string;
  cliente: Usuario;
  // veiculos: Veiculo[];
  formGroup: FormGroup;

  constructor(private activatedRoute: ActivatedRoute, private toastController: ToastController, private navController: NavController, private formBuilder: FormBuilder, private usuarioService: UsuarioService, private veiculoService: VeiculoService) {

    this.cliente = new Usuario();

    this.formGroup = this.formBuilder.group({
      'nome': [this.cliente?.nome, Validators.compose([
        Validators.required
      ])],
      'cpf': [this.cliente?.cpf, Validators.compose([
        Validators.required
      ])],
      'email': [this.cliente?.email, Validators.compose([
        Validators.required
      ])],
      'telefone': [this.cliente?.telefone, Validators.compose([
        Validators.required
      ])]
    });



    let id = this.activatedRoute.snapshot.params['id'];

    if (id != null) {
      this.titlePage = "Cliente";
      this.usuarioService.getById(id).then((json:any) => {
        this.cliente = <Usuario>(json);
        this.formGroup.get('nome')?.setValue(this.cliente.nome);
        this.formGroup.get('email')?.setValue(this.cliente.email);
        this.formGroup.get('cpf')?.setValue(this.cliente.cpf);
        this.formGroup.get('telefone')?.setValue(this.cliente.telefone);
      });
    } else {
      this.titlePage = "Cadastro de Cliente"
    }

    // this.veiculos = [];0
    // veiculoService.getByUser(this.cliente.id).then((json:any) =>{
    //   this.veiculos = <Veiculo[]>(json) || [];
    // })
    
    // if (id > 0) {
    //   this.clienteService.getById(id).then((json) => {
    //     this.cliente = <cliente>(json);
    //   });

    //   if (this.cliente)
    //     this.navController.navigateBack('/cliente');
    //   this.titlePage = "Cliente"
    // } else {
    //   this.titlePage = "Cadastro de Cliente"

    // }
  }

  ngOnInit() {
    if (UsuarioService.protect()) {
      this.navController.navigateBack('/login');
    }
  }

  bt_save() {
    this.cliente.nome = this.formGroup.value.nome;
    this.cliente.email = this.formGroup.value.email;
    this.cliente.cpf = this.formGroup.value.cpf;
    this.cliente.telefone = this.formGroup.value.telefone;
    this.cliente.senha = "0";
    this.cliente.permission = 'Cliente';

    this.usuarioService.checkEmail(this.cliente.email).then((json:any) => {
      let result = <number>(json);
      if (result === 404) {

        this.usuarioService.save(this.cliente)
          .then((json: any) => {
            this.cliente = <Usuario>(json);
            if (this.cliente) {
              this.showMessage('Registro salvo com sucesso!!!');
              this.navController.navigateBack('/cliente');
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