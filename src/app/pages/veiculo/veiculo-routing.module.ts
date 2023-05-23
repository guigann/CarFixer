import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { VeiculoPage } from './veiculo.page';

const routes: Routes = [
  {
    path: '',
    component: VeiculoPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VeiculoPageRoutingModule {}
