import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {MatError, MatFormField, MatFormFieldModule, MatLabel} from '@angular/material/form-field';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {
  MatDatepicker,
  MatDatepickerInput,
  MatDatepickerModule,
  MatDatepickerToggle
} from '@angular/material/datepicker';
import {MatInput, MatInputModule} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {NgForOf, NgIf} from '@angular/common';
import {EvenementService} from '../../services/evenement.service';
import {MatOption, provideNativeDateAdapter} from '@angular/material/core';
import {MatSelect} from '@angular/material/select';

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
    MatError,
    MatOption,
    MatSelect,
    NgForOf,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule
  ],
  templateUrl: './evenement.component.html',
  styleUrl: './evenement.css',
  providers: [provideNativeDateAdapter()],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EvenementComponent implements  OnInit {

  evenementForm!: FormGroup;

  eventErrorMessage='';
  eventSuccessMessage='';

  constructor(private form: FormBuilder, private evenementService: EvenementService) {
  }

  ngOnInit() {
    this.evenementForm = this.form.group({
      id: [''],
      nom: ['', Validators.required],
      date: ['', Validators.required],
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
      genre: this.evenementForm.value.genre && this.evenementForm.value.genre.length > 0
        ? this.evenementForm.value.genre.join(', ')
        : null,
      price: this.evenementForm.value.price
    };

    console.log("Formulaire en cours d'envoi...");
    this.evenementService.addEvenement(newEvent).subscribe({
      next: ( userInfo : {nom : string}) => {
        console.log(userInfo.nom + "créé avec succès");
        this.eventSuccessMessage = "Evénement créé avec succès";
        },

      error: (err: any) => {
        console.error("Erreur de création", err);
        console.log("Réponse API :", err);
        this.eventErrorMessage = "Échec de la création du événement";
      }
    });
  }

  genres: string[] = [
    'Rock', 'Pop', 'Jazz', 'Blues', 'Hip-hop', 'Classique', 'Reggae',
    'Electro', 'Metal', 'Folk', 'R&B', 'Country', 'Latino', 'Soul', 'Punk'
  ];


}



