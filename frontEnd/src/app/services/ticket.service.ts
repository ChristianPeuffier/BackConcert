import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private getUrl = 'http://localhost:8080/ticket/';
  private addUrl = 'http://localhost:8080/ticket/add';
  constructor(private http : HttpClient ) { }

  getTickets() {
    return this.http.get<any[]>(this.getUrl);
  }

  addTicket(newTicket: {
    prix: any;
    utilisateur: { id: number };
    evenement: { id: number };
    dateAchat: Date;
  }) {
    console.log("Envoi de la requête de création d'un ticket :", newTicket);
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post(this.addUrl, newTicket, {headers});
  }
}
