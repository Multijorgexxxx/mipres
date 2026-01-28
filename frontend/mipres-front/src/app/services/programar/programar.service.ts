import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  number: number;
  size: number;
}

@Injectable({
  providedIn: 'root'
})
export class ProgramarService {
  private apiUrl = '/api/direccionamientos'; // Ajusta a tu endpoint real

  constructor(private http: HttpClient) {}

  getData(page: number, size: number): Observable<PageResponse<any>> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size);

    return this.http.get<PageResponse<any>>(`${this.apiUrl}/pendientes-programacion/paged`, { params });
  }

  // Lógica simple para exportar (puedes usar librerías como XLSX)
  exportarArchivo(data: any[], formato: 'csv' | 'txt' | 'xls') {
    console.log(`Exportando ${data.length} registros en formato ${formato}`);
    // Aquí implementarías la lógica de descarga
  }
}