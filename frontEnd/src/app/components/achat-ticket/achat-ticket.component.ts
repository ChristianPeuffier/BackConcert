import {Component, OnInit} from '@angular/core';
import {MatButton} from '@angular/material/button';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
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

  constructor(private readonly route: ActivatedRoute,
              private readonly fb: FormBuilder,
              private readonly ticketService : TicketService,
              private readonly router: Router,
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
      next: (blob) => {
        // Traitement du fichier reçu
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'ticket.pdf';
        a.click();
        window.URL.revokeObjectURL(url);
        console.log('Téléchargement du PDF terminé');
        alert('Ticket acheté avec succès !');
        this.router.navigate(['/dashboard']).then(r =>  console.log("Redirection vers le dashboard"));
      },
      error: (err) => {
        console.error('Erreur lors du téléchargement du PDF :', err);
        alert('Une erreur est survenue. Veuillez réessayer.');
      },
      complete: () => {
        console.log('Téléchargement du PDF terminé');
      }
    });
  }}
