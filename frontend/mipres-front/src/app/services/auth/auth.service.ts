import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Usuario } from '../../models/usuario.model';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = '/api/login';

  constructor(private http: HttpClient) {}

login(credenciales: { usuario: string; contraseña: string }) {
  const params = new HttpParams()
    .set('usuario', credenciales.usuario)
    .set('contraseña', credenciales.contraseña);

  return this.http.get<Usuario>(this.apiUrl, { params }).pipe(
      tap(user => {
        console.log('Respuesta del servidor:', user);
        if (user && user.idRol !== undefined && user.id !== undefined) {
          localStorage.setItem('user_role', user.idRol.toString());
          localStorage.setItem('user_id', user.id.toString());
          localStorage.setItem('user_name', user.usuario);
        } else {
          console.warn('El servidor respondió, pero el objeto usuario está incompleto o es nulo.');
        }
      })
    );
  }
}