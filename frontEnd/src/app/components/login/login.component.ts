import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatButton} from '@angular/material/button';
import {MatInput} from '@angular/material/input';
import {MatDialogRef} from '@angular/material/dialog';
import {MatTab, MatTabGroup} from '@angular/material/tabs';


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
    MatLabel,
    MatTabGroup,
    MatTab
  ],
  styleUrls: ['./login.component.css'],


})
export class LoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  signupEmail = '';
  signupPassword = '';
  signupPrenom = '';
  signupNom = '';
  signupErrorMessage = '';
  signupSuccessMessage = '';

  constructor(private authService: AuthService, private router: Router, protected dialogRef: MatDialogRef<LoginComponent>) {}

  onLogin() {
    console.log("Tentative de connexion...");
    this.authService.login(this.email, this.password).subscribe({
      next: (response: { token: string; id: string; }) => {
        console.log("Token reçu :", response.token);
        // @ts-ignore
        this.authService.saveToken(response.token);
        this.authService.saveId(response.id);
        this.dialogRef.close();
      },
      error: (err: any) => {
        console.error("Erreur de connexion", err);
        console.log("Réponse API :", err);
        this.errorMessage = "Échec de la connexion. Vérifiez vos identifiants.";
      }

    });
  }

  onSignup() {
    const newUser = {
      email: this.signupEmail,
      password: this.signupPassword,
      prenom: this.signupPrenom,
      nom: this.signupNom
    };

    this.authService.signup(newUser).subscribe({
      next: (response: { message: string; }) => {
        console.log("Réponse du back :", response);
        this.signupSuccessMessage = response.message || "Inscription réussie ! Vous pouvez vous connecter.";
        this.signupErrorMessage = ""; // Effacer un éventuel message d'erreur
      },
      error: (err: { error: string; }) => {
        console.error("Erreur : ", err);
        this.signupErrorMessage = err.error || "Une erreur est survenue.";
        this.signupSuccessMessage = "";
      }
    });
  }

}
