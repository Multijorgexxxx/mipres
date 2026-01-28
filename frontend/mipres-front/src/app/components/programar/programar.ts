import { Component, OnInit, ChangeDetectorRef } from '@angular/core'; // 1. Importar ChangeDetectorRef
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProgramarService } from '../../services/programar/programar.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-programar',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './programar.html',
  styleUrls: ['./programar.css']
})
export class ProgramarComponent implements OnInit {
  data: any[] = [];
  filteredData: any[] = [];
  columnas: string[] = [];
  
  searchText: string = '';
  filtroSeleccionado: string = '';

  paginaActual: number = 1;
  registrosPorPagina: number = 10;
  totalPaginas: number = 1;
  totalRegistros: number = 0;
  datosPaginados: any[] = [];

  constructor(
    private service: ProgramarService, 
    private titleService: Title,
    private cdr: ChangeDetectorRef // 2. Inyectar el detector de cambios
  ) {
    this.titleService.setTitle("PROGRAMAR");
  }

  ngOnInit() {
    this.cargarDatos();
  }

  cargarDatos() {
    const page = this.paginaActual - 1;
    const size = this.registrosPorPagina;

    this.service.getData(page, size).subscribe({
      next: (res: any) => {
        const content = Array.isArray(res) ? res : (res?.content ?? []);

        // Actualizamos las referencias de los arrays
        this.data = [...content];
        this.filteredData = [...content];
        this.datosPaginados = [...content];

        if (Array.isArray(res)) {
          this.totalRegistros = res.length;
          this.totalPaginas = Math.max(1, Math.ceil(res.length / this.registrosPorPagina));
        } else {
          this.totalPaginas = res?.totalPages ?? 1;
          this.totalRegistros = res?.totalElements ?? content.length;
        }

        if (content.length > 0) {
          this.columnas = Object.keys(content[0]);
          // Solo asignar el filtro inicial si no hay uno seleccionado previamente
          if (!this.filtroSeleccionado) {
            this.filtroSeleccionado = this.columnas[0];
          }
        }

        // 3. OBLIGAR A ANGULAR A RENDERIZAR
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error cargando datos de programar:', err);
      }
    });
  }

  aplicarFiltro() {
    const term = this.searchText.toLowerCase();
    const filtrados = this.data.filter(item => {
      const valor = item[this.filtroSeleccionado]?.toString().toLowerCase() || '';
      return valor.includes(term);
    });
    
    this.paginaActual = 1;
    this.datosPaginados = filtrados;
    this.cdr.detectChanges(); // Refrescar tras filtrar
  }

  cambiarPagina(nuevaPagina: number) {
    if (nuevaPagina >= 1 && nuevaPagina <= this.totalPaginas) {
      this.paginaActual = nuevaPagina;
      this.cargarDatos();
    }
  }

  exportar(formato: 'csv' | 'txt' | 'xls') {
    this.service.exportarArchivo(this.data, formato);
  }

  accion(tipo: string, item: any) {
    console.log(`Acción ${tipo} sobre ID: ${item.ID}`);
  }

  trackById(index: number, item: any) {
    return item.ID; // O la llave primaria de tu registro
  }
}