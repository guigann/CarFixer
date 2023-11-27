import { Component, OnInit } from '@angular/core';
import { Veiculo } from '../veiculo/veiculo.page';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NavController, ToastController } from '@ionic/angular';
import { Usuario } from '../cliente/cliente.page';
import { UsuarioService } from 'src/app/services/usuario.service';
import { MaskitoElementPredicateAsync, MaskitoOptions } from '@maskito/core';

@Component({
  selector: 'app-add-veiculo',
  templateUrl: './add-veiculo.page.html',
  styleUrls: ['./add-veiculo.page.scss'],
})
export class AddVeiculoPage implements OnInit {
  veiculo: Veiculo;
  formGroup: FormGroup;
  clientes: Usuario[];

  constructor(private activatedRoute: ActivatedRoute, private toastController: ToastController, private navController: NavController, private formBuilder: FormBuilder, private veiculoService: VeiculoService, private usuarioService: UsuarioService) {

    this.veiculo = new Veiculo();

    this.formGroup = this.formBuilder.group({
      'placa': [this.veiculo.placa, Validators.compose([
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(8)
      ])],
      'modelo': [this.veiculo.modelo, Validators.compose([
        Validators.required
      ])],
      'tipo': [this.veiculo.tipo, Validators.compose([
        Validators.required
      ])],
      'id_cliente': [this.veiculo.id_cliente, Validators.compose([
        Validators.required
      ])]
    })

    let id = this.activatedRoute.snapshot.params['id'];
    if (id != null) {
      this.veiculoService.getById(parseFloat(id)).then((json) => {
        this.veiculo = <Veiculo>(json);
        this.formGroup.get('placa')?.setValue(this.veiculo.placa.slice(0, 3) + '-' + this.veiculo.placa.slice(3));
        this.formGroup.get('modelo')?.setValue(this.veiculo.modelo);
        this.formGroup.get('tipo')?.setValue(this.veiculo.tipo);
        this.formGroup.get('id_cliente')?.setValue(this.veiculo.id_cliente);
      });
    }

    this.clientes = []
    this.usuarioService.get().then((json: any) => {
      this.clientes = <Usuario[]>(json);
    })

    this.clientes.forEach((cliente) => {
      cliente.cpf = cliente.cpf.replace(/^(\d{3})(\d{3})(\d{3})(\d{2})$/, '$1.$2.$3-$4');
      cliente.telefone = cliente.telefone.replace(/^(\d{2})(\d{1})(\d{4})(\d{4})$/, '($1) $2 $3-$4');
    });
  }

  ngOnInit() {
    if (UsuarioService.protect()) {
      this.navController.navigateBack('/login');
    }
  }

  salvar() {
    this.veiculo.placa = this.formGroup.value.placa.replace(/-/g, '');
    this.veiculo.modelo = this.formGroup.value.modelo;
    this.veiculo.tipo = this.formGroup.value.tipo;
    this.veiculo.id_cliente = this.formGroup.value.id_cliente;

    this.veiculoService.checkPlaca(this.veiculo.placa).then((json) => {
      let result = <number>(json);
      if (result === 404) {
        this.veiculoService.save(this.veiculo)
          .then((json: any) => {
            this.veiculo = <Veiculo>(json);
            if (this.veiculo) {
              this.showMessage('Registro salvo com sucesso!!!');
              this.navController.navigateBack('/veiculo');
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
      this.showMessage('Erro ao verificar o placa! Erro:' + erro['mensage']);
    });

  }

  async showMessage(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }

  readonly placaMask: MaskitoOptions = {
    mask: [/^[a-zA-Z\s]/, /^[a-zA-Z\s]/, /^[a-zA-Z\s]/, '-', /\d/, /\d/, /\d/, /\d/],
    postprocessors: [
      ({ value, selection }) => ({ value: value.toUpperCase(), selection }),
    ],
  };

  readonly maskPredicate: MaskitoElementPredicateAsync = async (el) => (el as HTMLIonInputElement).getInputElement();
}