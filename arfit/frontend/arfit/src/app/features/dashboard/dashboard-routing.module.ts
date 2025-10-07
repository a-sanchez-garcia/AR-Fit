import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FeedComponent } from './feed/feed.component';
import { RutinasComponent } from './rutinas/rutinas.component';
import { EntrenamientoComponent } from './entrenamiento/entrenamiento.component';
import { PerfilComponent } from './perfil/perfil.component';
import { AjustesComponent } from './ajustes/ajustes.component';

const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    children: [
      { path: 'feed', component: FeedComponent },
      { path: 'rutinas', component: RutinasComponent },
      { path: 'entrenamiento', component: EntrenamientoComponent },
      { path: 'perfil', component: PerfilComponent },
      { path: 'ajustes', component: AjustesComponent },
      { path: '', redirectTo: 'feed', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule {}
