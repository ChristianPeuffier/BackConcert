import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private readonly getUrl = 'http://localhost:8080/ticket/user/';
  private readonly addUrl = 'http://localhost:8080/ticket/add';
  constructor(private readonly http : HttpClient ) { }

  getTickets(idUser: number, token: string | null) {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any[]>(this.getUrl + idUser, { headers });
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
    return this.http.post(this.addUrl, newTicket, {headers,responseType: 'blob'});
  }
}
