import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddAgendamentoPage } from './add-agendamento.page';

const routes: Routes = [
  {
    path: '',
    component: AddAgendamentoPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddAgendamentoPageRoutingModule {}
