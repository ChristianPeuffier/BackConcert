import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {NgForOf, NgIf} from '@angular/common';
import {EvenementService} from '../../services/evenement.service';
import {MatCard, MatCardContent, MatCardHeader,MatCardModule, MatCardTitle} from '@angular/material/card';
import {MatDivider} from '@angular/material/divider';
import {MatButton} from '@angular/material/button';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {LoginComponent} from '../login/login.component';
import {DetailEvenementComponent} from '../detail-evenement/detail-evenement.component';

@Component({
  selector: 'app-home',
  imports: [
    RouterLink,
    NgIf,
    NgForOf,
    MatCardTitle,
    MatCardHeader,
    MatCard,
    MatCardContent,
    MatDivider,
    MatCardModule,
    MatButton
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'

})
export class HomeComponent implements OnInit{

  userFirstName: string = '';
  evenements : any[] = [];


  constructor(protected authService: AuthService, private router: Router,
              private evenementService: EvenementService, private dialog: MatDialog) { }


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

    this.evenementService.getEvenements().subscribe(
      (data) => {
        this.evenements = data;
      }
    );
  }

  openEvenementDialog(evenement: any) {
    this.dialog.open(DetailEvenementComponent, {
      width: '90%', // S'adapte mieux sur mobile
      maxWidth: '400px', // Limite la largeur max
      height: 'auto', // Hauteur dynamique selon le contenu
      autoFocus: false,
      data: evenement
    });
  }


}


