import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms'; // <-- FormsModule añadido
import { DashboardRoutingModule } from './dashboard-routing.module';

import { DashboardComponent } from './dashboard/dashboard.component';
import { FeedComponent } from './feed/feed.component';
import { RutinasComponent } from './rutinas/rutinas.component';
import { EntrenamientoComponent } from './entrenamiento/entrenamiento.component';
import { PerfilComponent } from './perfil/perfil.component';
import { AjustesComponent } from './ajustes/ajustes.component';

@NgModule({
  declarations: [
    DashboardComponent,
    FeedComponent,
    RutinasComponent,
    EntrenamientoComponent,
    PerfilComponent,
    AjustesComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    DashboardRoutingModule,
    ReactiveFormsModule,
    FormsModule // <-- necesario para [(ngModel)]
  ]
})
export class DashboardModule { }
