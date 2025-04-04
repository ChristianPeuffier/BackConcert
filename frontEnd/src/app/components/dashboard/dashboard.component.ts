import {Component, OnInit} from '@angular/core';
import {MatCard, MatCardContent, MatCardTitle} from '@angular/material/card';
import {MatDivider, MatList, MatListItem} from '@angular/material/list';
import {MatToolbar} from '@angular/material/toolbar';
import {DatePipe, NgForOf, NgIf} from '@angular/common';
import {UtilisateurService} from '../../services/utilisateur.service';
import {AuthService} from '../../services/auth.service';
import {TicketService} from '../../services/ticket.service';
import {MatIcon} from '@angular/material/icon';
import {MatLine} from '@angular/material/core';
import {MatIconButton} from '@angular/material/button';

@Component({
  selector: 'app-dashboard',
  imports: [
    MatCard,
    MatCardTitle,
    MatToolbar,
    MatCardContent,
    DatePipe,
    MatDivider,
    MatIcon,
    MatIconButton,
    NgIf
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements  OnInit{
  user: any;
  tickets: any[] = [];

  constructor(private readonly utilisateurService : UtilisateurService, private readonly authService : AuthService, private readonly ticketService : TicketService) { }

  ngOnInit() {
    const token = localStorage.getItem('token') ?? '';
    this.utilisateurService.getUtilisateur( this.authService.getId(),token).subscribe({
      next: (user) => {
        this.user = user;
      },
      error: (err) => {
        console.error("Erreur de récupération des informations de l'utilisateur", err);
      }
    });

    this.ticketService.getTickets(Number(localStorage.getItem('id')),localStorage.getItem('token')).subscribe({
      next: (tickets) => {
        this.tickets = tickets;
        console.log("tickets récupérés :", this.tickets);
      },
      error: (err) => {
        console.error("Erreur de récupération des tickets", err);
      }
    });
  }
  currentIndex = 0;

  prev() {
    if (this.currentIndex > 0) {
      this.currentIndex--;
    }
  }

  next() {
    if (this.currentIndex < this.tickets.length - 1) {
      this.currentIndex++;
    }
  }

}
