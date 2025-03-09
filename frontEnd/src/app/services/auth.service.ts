import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/utilisateur/login';
  private userInfoUrl = 'http://localhost:8080/utilisateur/email';

  constructor(private http : HttpClient) { }

  login(email: string, password: string): Observable<any> {
    console.log("Envoi de la requête de login :", { email, password });
    return this.http.post('http://localhost:8080/utilisateur/login', { email, password });
  }


  saveToken(token: string){
    localStorage.setItem('token', token);
  }

  getToken(){
    return localStorage.getItem('token');
  }

  getUserInfo(): Observable<any> {
    const token = this.getToken();
    if (!token) {
      return new Observable(observer => observer.error('Token non trouvé'));
    }

    return this.http.get(this.userInfoUrl, {
      headers: { Authorization: `Bearer ${token}` }
    });
  }

  logout(){
    localStorage.removeItem('token');
  }

  isLoggedIn(): boolean{
    return !!this.getToken();
  }
}
