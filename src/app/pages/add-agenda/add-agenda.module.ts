import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AddAgendaPageRoutingModule } from './add-agenda-routing.module';

import { AddAgendaPage } from './add-agenda.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AddAgendaPageRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [AddAgendaPage]
})
export class AddAgendaPageModule {}
