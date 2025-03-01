import { Component } from '@angular/core';
import {UtilisateurService} from '../../services/utilisateur.service';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-utilisateur',
  templateUrl: './utilisateur.component.html',
  imports: [
    NgForOf
  ],
  styleUrl: './utilisateur.component.css'
})
export class UtilisateurComponent {
  utilisateurs : any[] = [];

  constructor(private utilisateurService: UtilisateurService) { }

  ngOnInit() {
    this.utilisateurService.getUtilisateurs().subscribe(
      (data) => {
        this.utilisateurs = data;
      }
    );
  }
}
