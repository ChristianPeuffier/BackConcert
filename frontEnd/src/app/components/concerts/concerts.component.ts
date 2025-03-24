import {Component, OnInit} from '@angular/core';
import {MatError, MatFormField, MatLabel} from '@angular/material/form-field';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from '@angular/material/datepicker';
import {MatInput} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {NgIf} from '@angular/common';

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
export class ConcertsComponent implements  OnInit{

  concertForm!: FormGroup;

  constructor(private form: FormBuilder) {}

  ngOnInit() {
    this.concertForm = this.form.group({
      nom: ['', Validators.required],
      date: ['', Validators.required],
      lieu: ['', Validators.required],
      prix: [0, [Validators.required, Validators.min(0)]],
      description: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  onSubmit() {
    if (this.concertForm.valid) {
      console.log("Concert créé avec succès :", this.concertForm.value);
    }
  }
}
