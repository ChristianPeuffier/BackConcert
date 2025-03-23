import { Routes } from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {UtilisateurComponent} from './components/utilisateur/utilisateur.component';
import {LoginComponent} from './components/login/login.component';
import {DashboardComponent} from './components/dashboard/dashboard.component';

export const routes: Routes = [
  {path: 'utilisateurs', component: UtilisateurComponent},
  {path: 'login', component: LoginComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: '', component: HomeComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'}
];
