import { Routes } from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {UtilisateurComponent} from './components/utilisateur/utilisateur.component';
import {LoginComponent} from './components/login/login.component';

export const routes: Routes = [
  {path: 'utilisateurs', component: UtilisateurComponent},
  {path: 'login', component: LoginComponent},
  {path: '', component: HomeComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'}
];
