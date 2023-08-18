import { Component, OnInit } from '@angular/core';
import { Usuario } from '../cliente/cliente.page';
import { UsuarioService } from 'src/app/services/usuario.service';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {
  usuario: Usuario;

  constructor(private navController: NavController) {
    this.usuario = UsuarioService.getLogin();
  }

  ngOnInit() {
    if (UsuarioService.protect()) {
      this.navController.navigateBack('/login');
    }
  }

}
