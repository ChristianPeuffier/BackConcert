import {Component, Inject, OnInit} from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import {AuthService} from '../../services/auth.service';
import {MatButton} from '@angular/material/button';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle
} from '@angular/material/dialog';

@Component({
  selector: 'app-detail-evenement',
  imports: [
    MatButton,
    RouterLink,
    MatDialogContent,
    MatDialogTitle,
    MatDialogActions
  ],
  templateUrl: './detail-evenement.component.html',
  styleUrl: './detail-evenement.component.css'
})
export class DetailEvenementComponent implements OnInit{
  constructor(protected authService: AuthService,public dialogRef: MatDialogRef<DetailEvenementComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private router : Router) {
  }

  ngOnInit() {
  }

  closeDialog() {
    this.dialogRef.close();
  }

  acheterTicket(evenement: any) {
    console.log("Tentative d'achat de ticket pour l'événement :", evenement);
    this.router.navigate(['/achatTicket'], {
      queryParams: {
        nom: evenement.nom,
        date: evenement.date,
        lieu: evenement.lieu,
        price: evenement.price,
        id: evenement.id,
        artiste: evenement.artiste,
        genre: evenement.genre,
      },
      queryParamsHandling: 'merge'
    }).then(r =>  console.log("Redirection vers la page d'achat de ticket"));
    this.dialogRef.close();
  }
}
