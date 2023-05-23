import { Component, OnInit } from '@angular/core';
import { Veiculo } from '../veiculo/veiculo.page';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { UserService } from 'src/app/services/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NavController, ToastController } from '@ionic/angular';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-add-veiculo',
  templateUrl: './add-veiculo.page.html',
  styleUrls: ['./add-veiculo.page.scss'],
})
export class AddVeiculoPage implements OnInit {
  veiculo: Veiculo;
  veiculoService: VeiculoService;
  userService: UserService;
  formGroup: FormGroup;
  clientes: User[];

  constructor(private activatedRoute: ActivatedRoute, private toastController: ToastController, private navController: NavController, private formBuilder: FormBuilder) {

    this.veiculo = new Veiculo();

    this.formGroup = this.formBuilder.group({
      'modelo': [this.veiculo.modelo, Validators.compose([
        Validators.required,
        Validators.minLength(5)
      ])],
      'placa': [this.veiculo.placa, Validators.compose([
        Validators.required
      ])],
      'tipo': [this.veiculo.tipo, Validators.compose([
        Validators.required
      ])],
      'user': [this.veiculo.user_id, Validators.compose([
        Validators.required
      ])]
    })

    this.veiculoService = new VeiculoService();
    let id = this.activatedRoute.snapshot.params['id'];

    if (id != null) {
      this.veiculo = this.veiculoService.getById(parseFloat(id));

      this.formGroup.get('modelo')?.setValue(this.veiculo.modelo);
      this.formGroup.get('placa')?.setValue(this.veiculo.placa);
      this.formGroup.get('tipo')?.setValue(this.veiculo.tipo);
      this.formGroup.get('user')?.setValue(this.veiculo.user_id);
    }

    this.userService = new UserService();
    this.clientes = this.userService.getClientes();
  }

  ngOnInit() {
  }

  salvar() {
    this.veiculo.modelo = this.formGroup.value.modelo;
    this.veiculo.placa = this.formGroup.value.placa;
    this.veiculo.tipo = this.formGroup.value.tipo;

    if (this.veiculoService.checkPlaca(this.veiculo)) {
      this.veiculoService.save(this.veiculo);
      this.exibirMensagem('Registro salvo com sucesso!!!');
      this.navController.navigateBack('/veiculo');
    } else {
      this.exibirMensagem('Já existe um veiculo com essa placa!!!');
    }

  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }
}