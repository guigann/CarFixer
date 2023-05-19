import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { VeiculoPageRoutingModule } from './veiculo-routing.module';

import { VeiculoPage } from './veiculo.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    VeiculoPageRoutingModule
  ],
  declarations: [VeiculoPage]
})
export class VeiculoPageModule {}
