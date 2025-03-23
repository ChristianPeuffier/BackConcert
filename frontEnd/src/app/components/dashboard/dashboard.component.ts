import {Component, OnInit} from '@angular/core';
import {MatCard, MatCardContent, MatCardTitle} from '@angular/material/card';
import {MatList, MatListItem} from '@angular/material/list';
import {MatToolbar} from '@angular/material/toolbar';
import {NgForOf} from '@angular/common';
import {UtilisateurService} from '../../services/utilisateur.service';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-dashboard',
  imports: [
    MatCard,
    MatCardTitle,
    MatList,
    MatListItem,
    MatToolbar,
    MatCardContent,
    NgForOf
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements  OnInit{
  user: any;
  tickets: any[] = [];

  constructor(private utilisateurService : UtilisateurService, private authService : AuthService) { }

  ngOnInit() {
    const token = localStorage.getItem('token') || '';
    this.utilisateurService.getUtilisateur( this.authService.getId(),token).subscribe({
      next: (user) => {
        this.user = user;
        this.tickets = user.tickets;
      },
      error: (err) => {
        console.error("Erreur de récupération des informations de l'utilisateur", err);
      }
    });

  }
}
