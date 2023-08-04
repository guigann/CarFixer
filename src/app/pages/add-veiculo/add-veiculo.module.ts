import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AddVeiculoPageRoutingModule } from './add-veiculo-routing.module';

import { AddVeiculoPage } from './add-veiculo.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AddVeiculoPageRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [AddVeiculoPage]
})
export class AddVeiculoPageModule {}
