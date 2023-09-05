import { Component, OnInit } from '@angular/core';
import { Agenda } from '../agenda/agenda.page';
import { AgendaService } from 'src/app/services/agenda.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NavController, ToastController } from '@ionic/angular';
import { VeiculoService } from 'src/app/services/veiculo.service';
import { Veiculo } from 'src/app/model/veiculo';
import { Servico } from 'src/app/model/servico';
import { ServicoService } from 'src/app/services/servico.service';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Horario } from 'src/app/model/horario';
import { HorarioService } from 'src/app/services/horario.service';

@Component({
  selector: 'app-add-agenda',
  templateUrl: './add-agenda.page.html',
  styleUrls: ['./add-agenda.page.scss'],
})
export class AddAgendaPage implements OnInit {
  usuario: Usuario;
  agenda: Agenda;
  formGroup: FormGroup;
  veiculos: Veiculo[];
  allServicos: Servico[];
  addedServicos: Servico[];
  allHorariosByDia: Horario[];
  horariosDisponiveis: Horario[];

  horariosOcupados: Horario[]; //teste

  //**** */
  // setando de forma implicita definições da tabela independente que ainda não foi implementada:
  horario_rangeMin: Date = new Date();
  horario_rangeMax: Date = new Date();
  horasPorAgendamento: number;
  //**** */

  constructor(
    private activatedRoute: ActivatedRoute,
    private toastController: ToastController,
    private navController: NavController,
    private formBuilder: FormBuilder,
    private agendaService: AgendaService,
    private veiculoService: VeiculoService,
    private servicoService: ServicoService,
    private usuarioService: UsuarioService,
    private horarioService: HorarioService
  ) {
    //**** */
    // setando de forma implicita definições da tabela independente que ainda não foi implementada:
    this.horario_rangeMin.setHours(10);
    this.horario_rangeMax.setHours(18);
    this.horasPorAgendamento = 1;
    //**** */

    this.horariosOcupados = [];
    this.horarioService.get().then((json) => {
      this.horariosOcupados = <Horario[]>json;
      console.log("horariosOcupados: ");
      console.log(this.horariosOcupados);
    });

    this.usuario = UsuarioService.getLogin();

    this.horariosDisponiveis = [];

    this.addedServicos = [];
    this.agenda = new Agenda();

    this.allServicos = [];
    this.servicoService.get().then((json) => {
      this.allServicos = <Servico[]>json;
    });

    this.allHorariosByDia = []; // --------------------- não esta filtrado por dia
    this.horarioService.get().then((json) => {
      this.allHorariosByDia = <Horario[]>json;
    });

    this.formGroup = this.formBuilder.group({
      id_horario: [
        this.agenda.id_horario,
        Validators.compose([Validators.required]),
      ],
      status: [this.agenda.status, Validators.compose([Validators.required])],
      id_veiculo: [
        this.agenda.id_veiculo,
        Validators.compose([Validators.required]),
      ],
      prevTermino: [
        this.agenda.dt_previsao,
        Validators.compose([Validators.required]),
      ],
      observacao: [this.agenda.observacao],
    });

    let id = this.activatedRoute.snapshot.params['id'];

    if (id != null) {
      this.agendaService.getById(parseFloat(id)).then((json) => {
        this.agenda = <Agenda>json;
        this.formGroup.get('id_horario')?.setValue(this.agenda.id_horario);
        this.formGroup.get('status')?.setValue(this.agenda.status);
        this.formGroup.get('veiculo')?.setValue(this.agenda.id_veiculo);
      });
    }

    this.veiculos = [];
    this.veiculoService.get().then((json: any) => {
      this.veiculos = <Veiculo[]>json;
    });
  }

  ngOnInit() {}

  salvar() {
    this.agenda.id_horario = this.formGroup.value.id_horario;
    // this.agenda.id_horario = 1;
    this.agenda.status = this.formGroup.value.status;
    this.agenda.id_veiculo = this.formGroup.value.id_veiculo;
    this.agenda.dt_previsao = this.formGroup.value.prevTermino;
    this.agenda.observacao = this.formGroup.value.observacao;
    this.agenda.produtos = [];

    console.log(
      'id_horario = ' +
        this.agenda.id_horario +
        'status = ' +
        this.agenda.status +
        'id_veiculo = ' +
        this.agenda.id_veiculo +
        'dt_previsao = ' +
        this.agenda.dt_previsao +
        'observacao = ' +
        this.agenda.observacao +
        'produtos = ' +
        this.agenda.produtos
    );

    this.agendaService.save(this.agenda);
    this.addedServicos.forEach((servico) => {
      // this.servicoService.save(servico);
    });
    this.exibirMensagem('Registro salvo com sucesso!!!');
    this.navController.navigateBack('/agenda');
  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500,
    });
    toast.present();
  }

  addServico() {
    let select: any = document.getElementById('servico');
    let idService = select.value;

    if (select.value > 0) {
      this.servicoService.getById(parseFloat(idService)).then((json: any) => {
        this.addedServicos.push(<Servico>json);
        this.allServicos = this.allServicos.filter(({ id }) => id != idService);
      });
    }

    select.value = '';
  }

  removeServico(servico: Servico) {
    this.addedServicos = this.addedServicos.filter(
      ({ id }) => id != servico.id
    );
    this.allServicos.push(servico);
  }

  calcularHorarios(dia: Date) {
    let horarios: Horario[] = [];
    let horasTotal =
      this.horario_rangeMax.getHours() - this.horario_rangeMin.getHours();

    for (
      let horasTrabalhando = 0;
      horasTrabalhando < horasTotal;
      horasTrabalhando += this.horasPorAgendamento
    ) {
      let horas = this.horario_rangeMin.getHours() + horasTrabalhando;
      dia.setHours(horas);

      let horario = new Horario(new Date(dia));
      horarios.push(horario);
    }

    return horarios;
  }

  loadHorariosDisponiveisPorDia() {
    let dataHTML: any = document.getElementById('inputData');
    let dataString = dataHTML.value;
    let diaSelecionado = new Date(dataString);

    let horariosDoDia = this.calcularHorarios(diaSelecionado);

    /**
     * obter do banco os horários já ocupados e comparar com o vetor
     */

    for (let i = 0; i < horariosDoDia.length; i++) {
      // for (let z = 0; z < this.horariosOcupados.length; z++) {
      //   let horarioOcupadoString = this.horariosOcupados[z].data.toDateString();
      //   if(horariosDoDia[i].data.toDateString() == horarioOcupadoString){
      //     horariosDoDia[i].status = this.horariosOcupados[z].status;
      //   }
        
      // }
      
    }

    // let horariosOcupadosDoDia = this.horariosOcupados.filter((a)=>a.data.getFullYear() === diaSelecionado.getFullYear());

    // console.log(horariosOcupadosDoDia);
    
    // horariosDoDia.filter((horario)=>{
    //   horariosOcupados.forEach(element => {
    //     horario.data ==
        
    //   });
    // })


    //              Falta mesclar a lista de horarios gerados com a lista de horarios do banco para assim listar corretamente no select Horario

    console.log(horariosDoDia);
    this.horariosDisponiveis = horariosDoDia;
    
  }

  isDataValida() {
    let dataHTML: any = document.getElementById('inputData');
    let dataString = dataHTML.value;

    let data = new Date(dataHTML.value);

    if (data != null || data != undefined) {
      return true;
    } else return false;
  }
}
