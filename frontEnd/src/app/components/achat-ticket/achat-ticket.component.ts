import {Component, Inject, OnInit} from '@angular/core';
import {
  MatCard,
  MatCardActions,
  MatCardContent,
  MatCardHeader,
  MatCardSubtitle,
  MatCardTitle
} from '@angular/material/card';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatButton} from '@angular/material/button';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {EvenementService} from '../../services/evenement.service';
import {FormsModule} from '@angular/forms';
import {MatInput} from '@angular/material/input';
import {AuthService} from '../../services/auth.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-achat-ticket',
  imports: [
    MatCardActions,
    MatButton,
    MatCardHeader,
    MatCard,
    MatCardContent,
    MatFormField,
    MatLabel,
    RouterLink,
    FormsModule,
    MatCardTitle,
    MatCardSubtitle,
    MatInput
  ],
  templateUrl: './achat-ticket.component.html',
  styleUrl: './achat-ticket.component.css'
})
export class AchatTicketComponent implements OnInit {
  evenement: any;
  quantite: number = 1;
  total: number = 0;

  constructor(private route: ActivatedRoute, private evenementService: EvenementService, protected authService: AuthService,
              @Inject(MAT_DIALOG_DATA) public data: any, private router : Router) {}

  ngOnInit() {
    const navigation = this.router.getCurrentNavigation();
    this.evenement = navigation?.extras.state?.['evenement'];

    if (!this.evenement) {
      // Si aucune donnée n'a été passée, rediriger vers la liste des événements
      this.router.navigate(['/evenements']);
    }

  }

  calculerTotal() {
    this.total = this.quantite * (this.evenement?.price || 0);
  }

  acheterTicket() {
    alert(`Achat confirmé ! Vous avez acheté ${this.quantite} ticket(s) pour ${this.evenement.nom}`);
    // Ici, tu peux ajouter une redirection ou un appel API pour finaliser l'achat
  }
}
