import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatButton} from '@angular/material/button';
import {MatInput} from '@angular/material/input';
import {MatDialogRef} from '@angular/material/dialog';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [
    FormsModule,
    NgIf,
    MatFormField,
    MatButton,
    MatInput,
    ReactiveFormsModule,
    MatLabel
  ],
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router, protected dialogRef: MatDialogRef<LoginComponent>) {}

  onLogin() {
    console.log("Tentative de connexion...");
    this.authService.login(this.email, this.password).subscribe({
      next: (response) => {
        console.log("Token reçu :", response.token);
        // @ts-ignore
        this.authService.saveToken(response.token);
        this.dialogRef.close();
      },
      error: (err) => {
        console.error("Erreur de connexion", err);
        console.log("Réponse API :", err);
        this.errorMessage = "Échec de la connexion. Vérifiez vos identifiants.";
      }

    });
  }



}
