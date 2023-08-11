import { Component, OnInit } from '@angular/core';
import { Usuario } from '../cliente/cliente.page';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  usuario: Usuario;
  
  constructor() {
    this.usuario = UsuarioService.getLogin();
  }

  ngOnInit() {
    UsuarioService.protect();
  }

}
