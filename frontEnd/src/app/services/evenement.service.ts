import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EvenementService {
  private apiUrl = 'http://localhost:8080/evenement/add';
  constructor( private http: HttpClient ) {}

  addEvenement(newEvent :
               {
                  nom: string,
                  date: Date,
                  lieu: string,
                  description: string,
                  artiste: string,
                  genre: string,
                  price: number
                }): Observable<any> {
    console.log("Envoi de la requête de création d'un événement :", newEvent);
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post(this.apiUrl, newEvent, {headers});
  }
}



