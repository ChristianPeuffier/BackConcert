import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {
  private apiUrl = 'http://localhost:8080/utilisateur';
  constructor(private http: HttpClient) { }

  getUtilisateurs(): Observable<any[]> {
    const token = localStorage.getItem('token');

    if (!token) {
      return new Observable(observer => observer.error('Token non trouvé'));
    }

    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any[]>(this.apiUrl, { headers });
  }

  getUtilisateur(id: number, token: string): Observable<any> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(`${this.apiUrl}/${id}`, { headers });
  }
}
