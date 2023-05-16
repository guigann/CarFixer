import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  @ViewChild('popover') popover:any;

  isOpen = false;
  public appPagesClientes =
    [{ title: 'Clientes', url: '/clientes', image: '' }]
    ;

  public appPagesVeiculos =
    [{ title: 'Veículos', url: '/veiculos', image: '' }];

  public appPagesAgendamentos =
    [{ title: 'Agendamentos', url: '/agendamentos', image: '' }];

  public appPagesServicos =
    [{ title: 'Serviços', url: '/servicos', image: '' }];

  ngOnInit() {
  }

  

  presentPopover(e: Event) {
    this.popover.event = e;
    this.isOpen = true;
  }

}