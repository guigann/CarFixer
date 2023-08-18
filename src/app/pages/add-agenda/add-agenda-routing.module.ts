import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddAgendaPage } from './add-agenda.page';

const routes: Routes = [
  {
    path: '',
    component: AddAgendaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddAgendaPageRoutingModule {}
