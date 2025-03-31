import { Routes } from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {UtilisateurComponent} from './components/utilisateur/utilisateur.component';
import {LoginComponent} from './components/login/login.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';
import {EvenementComponent} from './components/evenement/evenement.component';
import {AchatTicketComponent} from './components/achat-ticket/achat-ticket.component';

export const routes: Routes = [
  {path: 'utilisateurs', component: UtilisateurComponent},
  {path: 'login', component: LoginComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'evenement', component: EvenementComponent},
  {path: 'achatTicket', component: AchatTicketComponent},
  {path: '', component: HomeComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'}
];
