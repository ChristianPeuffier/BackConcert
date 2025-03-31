import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
// @ts-ignore
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/utilisateur/login';
  private userInfoUrl = 'http://localhost:8080/utilisateur/email';
  private userAdd = 'http://localhost:8080/utilisateur/add';

  constructor(private http : HttpClient) { }

  login(email: string, password: string): Observable<any> {
    console.log("Envoi de la requête de login :", { email, password });
    return this.http.post(this.apiUrl, { email, password });
  }


  saveToken(token: string){
    localStorage.setItem('token', token);
  }

  getToken(){
    return localStorage.getItem('token');
  }

  saveId(id: string){
    localStorage.setItem('id', id);
  }

  getId(): number {
    return parseInt(localStorage.getItem('id') || '0', 10);
  }

  getUserInfo(): Observable<any> {
    const token = this.getToken();
    if (!token) {
      return new Observable((observer: { error: (arg0: string) => any; }) => observer.error('Token non trouvé'));
    }

    return this.http.get(this.userInfoUrl, {
      headers: { Authorization: `Bearer ${token}` }
    });
  }

  logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
  }

  isLoggedIn(): boolean{
    return !!this.getToken();
  }

  signup(newUser: { email: string; password: string; prenom: string; nom: string }): Observable<any> {
    console.log("Envoi de la requête d'inscription :", newUser);
    return this.http.post(this.userAdd, newUser);
  }
}
