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
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatInput} from '@angular/material/input';
import {AuthService} from '../../services/auth.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MatStep, MatStepLabel, MatStepper, MatStepperNext, MatStepperPrevious} from '@angular/material/stepper';

@Component({
  selector: 'app-achat-ticket',
  imports: [
    MatButton,
    MatCardHeader,
    MatCard,
    MatCardContent,
    MatFormField,
    MatLabel,
    FormsModule,
    MatCardTitle,
    MatCardSubtitle,
    MatInput,
    MatStep,
    MatStepper,
    ReactiveFormsModule,
    MatStepperPrevious,
    MatStepLabel,
    MatStepperNext
  ],
  templateUrl: './achat-ticket.component.html',
  styleUrl: './achat-ticket.component.css'
})
export class AchatTicketComponent implements OnInit {
  evenement: any;
  step1Form!: FormGroup;
  quantite: number = 1;
  total: number = 0;

  constructor(private route: ActivatedRoute,
              private evenementService: EvenementService,
              protected authService: AuthService,
              private router: Router,
              private fb: FormBuilder) {}

  ngOnInit() {
    this.step1Form = this.fb.group({
      quantite: [1, [Validators.required, Validators.min(1)]]
    });
    this.route.queryParams.subscribe(params => {
      console.log("Params récupérés :", params);
      this.evenement = {
        nom: params['nom'],
        date: params['date'],
        lieu: params['lieu'],
        price: params['price'],
        description: params['description'],
        artiste: params['artiste'],
        genre: params['genre']
      };
    });
    console.log("Evenement récupéré :", this.evenement);
    this.calculerTotal();
  }

  calculerTotal() {
    this.total = this.quantite * (this.evenement?.price || 0);
  }

  acheterTicket() {
    alert(`Achat confirmé ! Vous avez acheté ${this.quantite} ticket(s) pour ${this.evenement.nom}`);
    // Ici, tu peux ajouter une redirection ou un appel API pour finaliser l'achat
  }
}
