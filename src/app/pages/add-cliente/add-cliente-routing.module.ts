import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddClientePage } from './add-cliente.page';

const routes: Routes = [
  {
    path: '',
    component: AddClientePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddClientePageRoutingModule {}
