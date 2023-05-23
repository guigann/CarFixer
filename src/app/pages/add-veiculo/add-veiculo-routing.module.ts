import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddVeiculoPage } from './add-veiculo.page';

const routes: Routes = [
  {
    path: '',
    component: AddVeiculoPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddVeiculoPageRoutingModule {}
