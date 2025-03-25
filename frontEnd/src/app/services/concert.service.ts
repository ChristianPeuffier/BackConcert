import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConcertService {
  private apiUrl = 'http://localhost:8080/evenement/add';
  constructor( private http: HttpClient ) {}

  addEvenement(newEvent :
               {
                  nom: string,
                  date: Date,
                  lieu: string,
                  description: string
                }): Observable<any> {
    console.log("Envoi de la requête de création de concert :", newEvent);
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post(this.apiUrl, newEvent, {headers});
  }
}



