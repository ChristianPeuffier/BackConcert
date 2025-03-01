import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {UtilisateurComponent} from './components/utilisateur/utilisateur.component';
import {NavbarComponent} from './components/navbar/navbar.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, UtilisateurComponent, RouterLink, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontEnd';
}
