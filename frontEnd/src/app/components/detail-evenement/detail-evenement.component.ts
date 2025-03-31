import {Component, Inject, OnInit} from '@angular/core';
import {
    MatCard,
    MatCardActions,
    MatCardContent,
    MatCardHeader,
    MatCardSubtitle,
    MatCardTitle
} from "@angular/material/card";
import {MatDivider} from "@angular/material/divider";
import {NgForOf, NgIf} from "@angular/common";
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
    MatCard,
    MatCardContent,
    MatCardHeader,
    MatCardSubtitle,
    MatCardTitle,
    MatDivider,
    NgForOf,
    NgIf,
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
    this.dialogRef.close();
    this.router.navigate(['/achatTicket'], {state: {evenement}}).then(r => console.log("Redirection vers la page d'achat de ticket"));
   }
}
