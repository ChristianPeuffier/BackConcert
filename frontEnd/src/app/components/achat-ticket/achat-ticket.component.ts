import {Component, Inject, OnInit} from '@angular/core';
import {MatButton} from '@angular/material/button';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {EvenementService} from '../../services/evenement.service';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {MatStep, MatStepLabel, MatStepper, MatStepperNext, MatStepperPrevious} from '@angular/material/stepper';
import {TicketService} from '../../services/ticket.service';

@Component({
  selector: 'app-achat-ticket',
  imports: [
    MatButton,
    FormsModule,
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
              private fb: FormBuilder,
              private ticketService : TicketService,
              private router: Router,
  ) {}

  ngOnInit() {
    this.step1Form = this.fb.group({
      quantite: [1, [Validators.required, Validators.min(1)]]
    });
    this.route.queryParams.subscribe((params: { [x: string]: any; }) => {
      console.log("Params récupérés :", params);
      this.evenement = {
        idEvenement: params['idEvenement'],
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
    console.log(`Achat confirmé ! Vous avez acheté ${this.quantite} ticket(s) pour ${this.evenement.nom}`);


    this.ticketService.addTicket( {
      prix : this.evenement.price,
      utilisateur: {id: Number(localStorage.getItem('id'))},
      evenement: {id: Number(this.evenement.idEvenement)},
      dateAchat: new Date()
    }).subscribe({
      next: (response) => {
        console.log("Réponse du serveur :",  response);
        this.router.navigate(['/home']).then(r =>  console.log("Redirection vers la page d'accueil"));
        alert("Achat effectué avec succès !");
      },
      error: (error) => {
        console.error("Erreur lors de l'achat :", error);
        alert("Erreur lors de l'achat. Veuillez réessayer.");
      }
    });
  }}
