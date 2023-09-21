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
  horariosDisponiveis: Horario[];
  horariosOcupados: Horario[];

  isEditing: boolean = false;

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
      console.log('horariosOcupados: ');
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

    this.veiculos = [];
    this.veiculoService.get().then((json: any) => {
      this.veiculos = <Veiculo[]>json;
    });

    let id = this.activatedRoute.snapshot.params['id'];

    if (id != null) {
      this.agendaService.getById(parseFloat(id)).then((json) => {
        this.agenda = <Agenda>json;
        this.formGroup.get('status')?.setValue(this.agenda.status);
        this.formGroup.get('veiculo')?.setValue(this.agenda.id_veiculo);
        this.formGroup.get('prevTermino')?.setValue(this.agenda.dt_previsao);
        this.formGroup.get('observacao')?.setValue(this.agenda.observacao);

        this.horarioService
          .getById(this.agenda.id_horario)
          .then((json: any) => {
            let inputData: any = document.getElementById('inputData');
            let horario = <Horario>json;
            let data = new Date(horario.data);
            let [dia, mes, ano] = [
              data.getDate(),
              data.getMonth(),
              data.getFullYear(),
            ];

            inputData.value =
              ano +
              '-' +
              this.twoDigitsCheck(mes + 1) +
              '-' +
              this.twoDigitsCheck(dia);

            let horarioSelect: any = document.getElementById('horario');
            let [horas, minutos] = [data.getHours(), data.getMinutes()];
            horarioSelect.selectedText =
              horas + 'h' + this.twoDigitsCheck(minutos);

            inputData.readonly = true;
            horarioSelect.disabled = true;
          });

        this.enableSelectVeiculoEditing(false);

        this.servicoService.getByIdAgenda(this.agenda.id).then((json: any) => {
          this.addedServicos = <Servico[]>json;
        });

        this.enableSelectStatusEditing(false);
      });
    }

    this.formGroup = this.formBuilder.group({
      horario: ['', Validators.compose([Validators.required])],
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
  }

  ngOnInit() {}

  ionViewWillEnter() {
    this.isEditing = false;
  }

  salvar() {
    let dataSelect = new Date(this.formGroup.value.horario);

    this.horarioService.save(new Horario(dataSelect)).then((json) => {
      let horarioSalvo = <Horario>json;
      console.log('horario pre-salvo no banco: ');
      console.log(horarioSalvo);

      this.agenda.id_horario = horarioSalvo.id;
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

      this.agendaService.save(this.agenda).then((json) => {
        let agenda = <Agenda>json;

        horarioSalvo.status = 'Ocupado';
        this.horarioService.save(horarioSalvo);

        this.addedServicos.forEach((servico) => {
          this.servicoService.putOnAgenda(agenda.id, servico.id);
        });
        // se servicos não forem salvos, exibir msg de erro
        this.exibirMensagem('Registro salvo com sucesso!!!');
        this.navController.navigateBack('/agenda');
      });
    });
    // se agenda nn for salva o horario salvo no banco deverá ser deletado do banco
  }

  bt_update(){
    console.log(this.agenda);
    this.agenda.status = this.formGroup.value.status || this.agenda.status;
    this.agenda.id_veiculo = this.formGroup.value.id_veiculo || this.agenda.id_veiculo;
    this.agenda.dt_previsao = this.formGroup.value.prevTermino || this.agenda.dt_previsao;
    this.agenda.observacao = this.formGroup.value.observacao || this.agenda.observacao;
    
    console.log(this.agenda);
    
    this.agendaService.save(this.agenda).then((json) => {
      let agenda = <Agenda>json;

      this.servicoService.getByIdAgenda(this.agenda.id).then((json: any) => {
        let servicos = <Servico[]>json;
        if (servicos.length > 0) {
          this.servicoService.deleteAllFromAgenda(agenda.id).then(_=>{
            if (this.addedServicos.length > 0) {
              this.addedServicos.forEach((servico) => {
                this.servicoService.putOnAgenda(agenda.id, servico.id);
              });
            }
          });
        } else {
          this.addedServicos.forEach((servico) => {
            this.servicoService.putOnAgenda(agenda.id, servico.id);
          });
        }
      });


      /**
       * add produtos
       */

      this.exibirMensagem('Registro salvo com sucesso!!!');
      window.location.reload();
    });
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
    diaSelecionado.setDate(diaSelecionado.getDate() + 1);

    let horariosDoDia = this.calcularHorarios(diaSelecionado);

    for (let i = 0; i < horariosDoDia.length; i++) {
      for (let z = 0; z < this.horariosOcupados.length; z++) {
        let horarioOcupado = this.horariosOcupados[z];
        // console.log('horarioOcupado.data :');
        // console.log(horarioOcupado);

        // let val1 = horariosDoDia[i].data;
        let val1 = new Date(
          horariosDoDia[i].data.getTime() +
            horariosDoDia[i].data.getTimezoneOffset() * 60000
        );
        let val2 = new Date(horarioOcupado.data);
        // const dataHoraUtc = new Date(dataHora.getTime() + (dataHora.getTimezoneOffset() * 60000));

        let objVal1 = {
          year: val1.getFullYear(),
          month: val1.getMonth(), // lembrando q vai de 0 a 11
          day: val1.getDate(),
          hour: val1.getHours(),
          minute: val1.getMinutes(),
          second: val1.getSeconds(),
        };
        let objVal2 = {
          year: val2.getUTCFullYear(),
          month: val2.getUTCMonth(),
          day: val2.getUTCDate(),
          hour: val2.getUTCHours(),
          minute: val2.getUTCMinutes(),
          second: val2.getUTCSeconds(),
        };
        
        if (objVal1.month === objVal2.month) {
          if (objVal1.day === objVal2.day) {
            if (objVal1.hour === objVal2.hour) {
              if (objVal1.minute === objVal2.minute) {
                horariosDoDia[i].status = horarioOcupado.status;
                
                console.log('horarios iguais: ');
                console.log(objVal1);
                console.log(objVal2);
              }
            }
          }
        }
      }
    }

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

  twoDigitsCheck(num: number) {
    return num >= 10 ? num : `0${num}`;
  }

  bt_editing() {
    this.isEditing = !this.isEditing;
    this.enableSelectStatusEditing(this.isEditing);
    this.enableSelectVeiculoEditing(this.isEditing);

    if (!this.isEditing) {
      window.location.reload();
    }
  }

  enableSelectStatusEditing(state: boolean) {
    let selectStatus: any = document.getElementById('status');
    if (state) {
      selectStatus.selectedText = '';
      selectStatus.disabled = false;
    } else {
      selectStatus.selectedText = this.agenda.status.replace('_', ' ');
      selectStatus.disabled = true;
    }
  }
  enableSelectVeiculoEditing(state: boolean) {
    let selectVeiculo: any = document.getElementById('veiculo');
    if (state) {
      this.veiculoService.getById(this.agenda.id_veiculo).then((json: any) => {
        let veiculo = <Veiculo>json;
        selectVeiculo.selectedText = `${veiculo.modelo} - ${veiculo.placa}`; // isso impede de alterar veículo, devera ser repensado
      });
      selectVeiculo.disabled = false;
    } else {
      this.veiculoService.getById(this.agenda.id_veiculo).then((json: any) => {
        let veiculo = <Veiculo>json;

        selectVeiculo.selectedText = `${veiculo.modelo} - ${veiculo.placa}`;

        selectVeiculo.disabled = true;
      });
    }
  }
}
