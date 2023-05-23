import { Component, OnInit } from '@angular/core';
import { Agendamento } from '../agendamento/agendamento.page';
import { AgendamentoService } from 'src/app/services/agendamento.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NavController, ToastController } from '@ionic/angular';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { Veiculo } from 'src/app/model/veiculo';
import { Tipo_ServicoService } from 'src/app/services/tipo_servico.service';
import { Tipo_Servico } from 'src/app/model/tipo_servico';

@Component({
  selector: 'app-add-agendamento',
  templateUrl: './add-agendamento.page.html',
  styleUrls: ['./add-agendamento.page.scss'],
})
export class AddAgendamentoPage implements OnInit {
  agendamento: Agendamento;
  agendamentoService: AgendamentoService;
  veiculoService: VeiculoService;
  tipo_servicoService: Tipo_ServicoService;
  formGroup: FormGroup;
  veiculos: Veiculo[];
  tipo_servicos: Tipo_Servico[];

  constructor(private activatedRoute: ActivatedRoute, private toastController: ToastController, private navController: NavController, private formBuilder: FormBuilder) {

    this.agendamento = new Agendamento();

    this.formGroup = this.formBuilder.group({
      'data': [this.agendamento.data, Validators.compose([
        Validators.required,
        Validators.minLength(5)
      ])],
      'status': [this.agendamento.status, Validators.compose([
        Validators.required
      ])],
      'veiculo': [this.agendamento.veiculo_id, Validators.compose([
        Validators.required
      ])],
      'tipo_servico': [this.agendamento.tipo_servico_id, Validators.compose([
        Validators.required
      ])]
    })

    this.agendamentoService = new AgendamentoService();
    let id = this.activatedRoute.snapshot.params['id'];

    if (id != null) {
      this.agendamento = this.agendamentoService.getById(parseFloat(id));

      this.formGroup.get('data')?.setValue(this.agendamento.data);
      this.formGroup.get('status')?.setValue(this.agendamento.status);
      this.formGroup.get('veiculo')?.setValue(this.agendamento.veiculo_id);
      this.formGroup.get('tipo_servico')?.setValue(this.agendamento.tipo_servico_id);
    }

    this.veiculoService = new VeiculoService();
    this.veiculos = this.veiculoService.getAll();
    this.tipo_servicoService = new Tipo_ServicoService();
    this.tipo_servicos = this.tipo_servicoService.getAll();
  }

  ngOnInit() {
  }

  salvar() {
    this.agendamento.data = this.formGroup.value.data;
    this.agendamento.status = this.formGroup.value.status;
    this.agendamento.veiculo_id = this.formGroup.value.veiculo_id;
    this.agendamento.tipo_servico_id = this.formGroup.value.tipo_servico_id;


    this.agendamentoService.save(this.agendamento);
    this.exibirMensagem('Registro salvo com sucesso!!!');
    this.navController.navigateBack('/agendamento');
  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }
}