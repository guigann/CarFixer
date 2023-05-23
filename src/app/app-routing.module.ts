import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./pages/home/home.module').then( m => m.HomePageModule)
  },
  {
    path: 'agendamento',
    loadChildren: () => import('./pages/agendamento/agendamento.module').then( m => m.AgendamentoPageModule)
  },
  {
    path: 'add-agendamento',
    loadChildren: () => import('./pages/add-agendamento/add-agendamento.module').then( m => m.AddAgendamentoPageModule)
  },
  {
    path: 'add-agendamento/:id',
    loadChildren: () => import('./pages/add-agendamento/add-agendamento.module').then( m => m.AddAgendamentoPageModule)
  },


];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }