import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProgramarService {
  private apiUrl = 'api/direccionamientos'; // Ajusta a tu endpoint real

  constructor(private http: HttpClient) {}

  getData(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/pendientes-programacion`);
  }

  // Lógica simple para exportar (puedes usar librerías como XLSX)
  exportarArchivo(data: any[], formato: 'csv' | 'txt' | 'xls') {
    console.log(`Exportando ${data.length} registros en formato ${formato}`);
    // Aquí implementarías la lógica de descarga
  }
}