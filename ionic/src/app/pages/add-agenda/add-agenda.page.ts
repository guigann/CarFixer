import { Component, OnInit } from '@angular/core';
import { Agenda } from '../agenda/agenda.page';
import { AgendaService } from 'src/app/services/agenda.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NavController, ToastController } from '@ionic/angular';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { Veiculo } from 'src/app/model/veiculo';
import { Tipo_ServicoService } from 'src/app/services/tipo_servico.service';
import { Tipo_Servico } from 'src/app/model/tipo_servico';

@Component({
  selector: 'app-add-agenda',
  templateUrl: './add-agenda.page.html',
  styleUrls: ['./add-agenda.page.scss'],
})
export class AddAgendaPage implements OnInit {
  agenda: Agenda;
  formGroup: FormGroup;
  veiculos: Veiculo[];
  // tipo_servicos: Tipo_Servico[];

  constructor(private activatedRoute: ActivatedRoute, private toastController: ToastController, private navController: NavController, private formBuilder: FormBuilder, private agendaService : AgendaService, private veiculoService:VeiculoService) {

    this.agenda = new Agenda();

    this.formGroup = this.formBuilder.group({
      'id_horario': [this.agenda.id_horario, Validators.compose([
        Validators.required,
        Validators.minLength(5)
      ])],
      'status': [this.agenda.status, Validators.compose([
        Validators.required
      ])],
      'veiculo': [this.agenda.id_veiculo, Validators.compose([
        Validators.required
      ])],
      // 'tipo_servico': [this.agenda.tipo_servico_id, Validators.compose([
      //   Validators.required
      // ])]
    })

    let id = this.activatedRoute.snapshot.params['id'];

    if (id != null) {
      this.agendaService.getById(parseFloat(id)).then((json) => {
        this.agenda = <Agenda>(json);
        this.formGroup.get('id_horario')?.setValue(this.agenda.id_horario);
      this.formGroup.get('status')?.setValue(this.agenda.status);
      this.formGroup.get('veiculo')?.setValue(this.agenda.id_veiculo);
      // this.formGroup.get('tipo_servico')?.setValue(this.agenda.tipo_servico_id);
      });
    }

    this.veiculos = []
    this.veiculoService.get().then((json: any) => { //colocar getByUser
      this.veiculos = <Veiculo[]>(json);
    })
   
  }

  ngOnInit() {
  }

  salvar() {
    this.agenda.id_horario = this.formGroup.value.id_horario;
    this.agenda.status = this.formGroup.value.status;
    this.agenda.id_veiculo = this.formGroup.value.id_veiculo;
    // this.agenda.tipo_servico_id = this.formGroup.value.tipo_servico_id;


    this.agendaService.save(this.agenda);
    this.exibirMensagem('Registro salvo com sucesso!!!');
    this.navController.navigateBack('/agenda');
  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }
}