import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [
    RouterLink,
    NgIf
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  userFirstName: string = '';

  constructor(protected authService: AuthService, private router: Router) { }


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
}
