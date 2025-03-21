import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {NgClass, NgIf, NgOptimizedImage} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {MatMenu, MatMenuItem, MatMenuTrigger} from '@angular/material/menu';
import {MatButton, MatIconButton} from '@angular/material/button';
import {MatFormField} from '@angular/material/form-field';
import {MatIcon} from '@angular/material/icon';
import {MatInput} from '@angular/material/input';
import {MatToolbar} from '@angular/material/toolbar';
import {MatDialog} from '@angular/material/dialog';
import {LoginComponent} from '../login/login.component';

@Component({
  selector: 'app-navbar',
  imports: [
    RouterLink,
    NgOptimizedImage,
    FormsModule,
    NgIf,
    MatMenu,
    MatMenuTrigger,
    MatButton,
    MatMenuItem,
    MatFormField,
    MatIcon,
    MatInput,
    MatIconButton,
    MatToolbar,
    NgClass
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{

  userFirstName: string = '';
  searchQuery: string = '';

  constructor(protected authService: AuthService, private router: Router, private dialog: MatDialog) { }

  onSearch() {
    console.log(this.searchQuery);
  }

  ngOnInit() {
    if(this.authService.isLoggedIn()){
      this.authService.getUserInfo().subscribe({
        next:(userInfo) => {
          this.userFirstName = userInfo.prenom;
        },
        error: (err) => {
          console.error("Erreur de récupération des informations de l'utilisateur", err);
        }
      });
    }
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/login']).then(r => {});
  }

  openLoginDialog() {
    this.dialog.open(LoginComponent, {
      width: '90%', // S'adapte mieux sur mobile
      maxWidth: '400px', // Limite la largeur max
      height: 'auto', // Hauteur dynamique selon le contenu
      autoFocus: false // Évite que le clavier prenne tout l'écran sur mobile
    });
  }


  menuOpen = false;

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  searchOpen = false;

  toggleSearch() {
    this.searchOpen = !this.searchOpen;
  }

}
