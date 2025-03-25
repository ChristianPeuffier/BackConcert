import {Component, OnInit} from '@angular/core';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from '@angular/material/datepicker';
import {MatInput} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {NgIf} from '@angular/common';
import {ConcertService} from '../../services/concert.service';

@Component({
  selector: 'app-concerts',
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
  templateUrl: './concerts.component.html',
  styleUrl: './concerts.component.css'
})
export class ConcertsComponent implements  OnInit {

  concertForm!: FormGroup;

  eventErrorMessage='';
  eventSuccessMessage='';

  constructor(private form: FormBuilder, private concertService: ConcertService) {
  }

  ngOnInit() {
    this.concertForm = this.form.group({
      nom: ['', Validators.required],
      date: [''],
      lieu: ['', Validators.required],
      description: ['', [Validators.required, Validators.minLength(10)]]
    });
  }


  onSubmit() {
    const newEvent = {
      nom: this.concertForm.value.nom,
      date: this.concertForm.value.date,
      lieu: this.concertForm.value.lieu,
      description: this.concertForm.value.description
    };

    console.log("Formulaire en cours d'envoi...");
    this.concertService.addEvenement(newEvent).subscribe({
      next: (response) => {
        console.log("Concert créé avec succès");
        this.eventSuccessMessage = "Concert créé avec succès";

      },

      error: (err) => {
        console.error("Erreur de création", err);
        console.log("Réponse API :", err);
        this.eventErrorMessage = "Échec de la création du concert";
      }
    });
  }
}



