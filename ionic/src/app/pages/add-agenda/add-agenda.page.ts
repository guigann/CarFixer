import { Component, OnInit } from '@angular/core';
import { Agenda } from '../agenda/agenda.page';
import { AgendaService } from 'src/app/services/agenda.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NavController, ToastController } from '@ionic/angular';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { Veiculo } from 'src/app/model/veiculo';
import { Servico } from 'src/app/model/servico';
// import { Tipo_ServicoService } from 'src/app/services/tipo_servico.service';
// import { Tipo_Servico } from 'src/app/model/tipo_servico';

@Component({
  selector: 'app-add-agenda',
  templateUrl: './add-agenda.page.html',
  styleUrls: ['./add-agenda.page.scss'],
})
export class AddAgendaPage implements OnInit {
  agenda: Agenda;
  formGroup: FormGroup;
  veiculos: Veiculo[];
  servicos: Servico[];
  addServico: Servico;

  constructor(private activatedRoute: ActivatedRoute, private toastController: ToastController, private navController: NavController, private formBuilder: FormBuilder, private agendaService: AgendaService, private veiculoService: VeiculoService) {

    this.agenda = new Agenda();
    this.servicos = [new Servico("Lanternagem", "klsdjfklds"), new Servico("Pintura", "klsdjfklds")];

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
      ])]
    })

    let id = this.activatedRoute.snapshot.params['id'];

    if (id != null) {
      this.agendaService.getById(parseFloat(id)).then((json) => {
        this.agenda = <Agenda>(json);
        this.formGroup.get('id_horario')?.setValue(this.agenda.id_horario);
        this.formGroup.get('status')?.setValue(this.agenda.status);
        this.formGroup.get('veiculo')?.setValue(this.agenda.id_veiculo);
        this.formGroup.get('servico')?.setValue(this.servicos.id_servico);
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


    this.agendaService.save(this.agenda);
    //servicoService.saveArray(this.servicos);
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

  addServico(){
    let servico = document.querySelector('#servico');

    console.log(servico);
    
    // this.servicos.push()
  }
}