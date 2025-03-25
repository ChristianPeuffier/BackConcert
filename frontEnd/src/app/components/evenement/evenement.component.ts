import {Component, OnInit} from '@angular/core';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from '@angular/material/datepicker';
import {MatInput} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {NgIf} from '@angular/common';
import {EvenementService} from '../../services/evenement.service';

@Component({
  selector: 'app-evenement',
  imports: [
    MatFormField,
    ReactiveFormsModule,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatDatepicker,
    MatInput,
    MatButton,
    NgIf,
    MatLabel,
    MatError
  ],
  templateUrl: './evenement.component.html',
  styleUrl: './evenement.css'
})
export class EvenementComponent implements  OnInit {

  evenementForm!: FormGroup;

  eventErrorMessage='';
  eventSuccessMessage='';

  constructor(private form: FormBuilder, private evenementService: EvenementService) {
  }

  ngOnInit() {
    this.evenementForm = this.form.group({
      nom: ['', Validators.required],
      date: [''],
      lieu: ['', Validators.required],
      description: ['', [Validators.required, Validators.minLength(10)]],
      artiste: [''],
      genre: [''],
      price: ['']
    });
  }


  onSubmit() {
    const newEvent = {
      nom: this.evenementForm.value.nom,
      date: this.evenementForm.value.date,
      lieu: this.evenementForm.value.lieu,
      description: this.evenementForm.value.description,
      artiste: this.evenementForm.value.artiste,
      genre: this.evenementForm.value.genre,
      price: this.evenementForm.value.price
    };

    console.log("Formulaire en cours d'envoi...");
    this.evenementService.addEvenement(newEvent).subscribe({
      next: (response) => {
        console.log("Evénement créé avec succès");
        this.eventSuccessMessage = "Evénement créé avec succès";

      },

      error: (err) => {
        console.error("Erreur de création", err);
        console.log("Réponse API :", err);
        this.eventErrorMessage = "Échec de la création du événement";
      }
    });
  }
}



