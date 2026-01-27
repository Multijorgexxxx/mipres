import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { MenuItem } from '../../models/menu.model';
import { HttpParams } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class MenuService {
  private apiUrl = '/api/menus/role-id';
  private menuCache: MenuItem[] = [];

  constructor(private http: HttpClient) {}

  /**
   * Carga los menús filtrados por rol desde el backend.
   * @param idRol El nombre del rol (ej. 'ADMIN', 'USER')
   */
  cargarMenus(idRol: number = 1): Observable<MenuItem[]> {
    console.log('--- Iniciando carga de menús para el rol:', idRol);
    if (this.menuCache.length > 0) {
      console.log('✅ Cargando desde caché:', this.menuCache);
      return of(this.menuCache);
    }

    const params = new HttpParams().set('idRol', idRol.toString());

    return this.http.get<any>(this.apiUrl, { params }).pipe(
      map(response => {
        if (!response) {
          console.log('⚠️ No se recibió respuesta del servidor');
          return [];
        }

        const menusRaw: MenuItem[] = Array.isArray(response)
          ? response
          : (response.content || []);
        console.log('📋 Menús raw recibidos:', menusRaw);

        return this.construirJerarquia(menusRaw);
      }),
      tap(menuTree => {
        this.menuCache = menuTree;
        console.log('✅ Menús cargados y almacenados en caché:', menuTree);
      }),
      catchError(error => {
        console.error('Error al cargar menús desde el servidor:', error);
        return of([]);
      })
    );
  }

  /**
   * Convierte la lista plana de la DB en una estructura de árbol.
   * Regla: menu_padre_id === 0 son los nodos raíz.
   */
  private construirJerarquia(menus: MenuItem[]): MenuItem[] {
    const menuMap = new Map<number, MenuItem>();
    const raices: MenuItem[] = [];

    menus.forEach(menu => {
      menuMap.set(menu.id, { ...menu, submenus: [] });
    });

    menuMap.forEach(item => {
      if (item.menuPadreId === 0) {
        raices.push(item);
      } else {
        const padre = menuMap.get(item.menuPadreId);
        if (padre) {
          padre.submenus!.push(item);
        }
      }
    });

    return raices.sort((a, b) => a.orden - b.orden);
  }

  /**
   * Limpia el caché para forzar una nueva recarga (útil al cambiar de usuario/rol)
   */
  limpiarCache(): void {
    this.menuCache = [];
    console.log('✅ Caché de menús limpiado');
  }
}