import { Component, OnInit } from '@angular/core';
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
  columnas: string[] = []; // Se llenará dinámicamente
  
  // Filtros
  searchText: string = '';
  filtroSeleccionado: string = '';

  paginaActual: number = 1;
  registrosPorPagina: number = 10;
  totalPaginas: number = 1;
  datosPaginados: any[] = [];
  
  constructor(private service: ProgramarService, private titleService: Title) {
    this.titleService.setTitle("PROGRAMAR"); // Título de la pestaña
  }

  ngOnInit() {
    this.cargarDatos();
  }

  cargarDatos() {
    this.service.getData().subscribe(res => {
      this.data = res;
      this.filteredData = res;
      
      if (res.length > 0) {
        this.columnas = Object.keys(res[0]);
        this.filtroSeleccionado = this.columnas[0];
        
        this.actualizarPaginacion(this.filteredData); 
      }
    });
  }

  aplicarFiltro() {
    const filtrados = this.data.filter(item => {
      const valor = item[this.filtroSeleccionado]?.toString().toLowerCase() || '';
      return valor.includes(this.searchText.toLowerCase());
    });
    
    this.paginaActual = 1;
    this.actualizarPaginacion(filtrados);
  }

  actualizarPaginacion(datos: any[]) {
    this.totalPaginas = Math.ceil(datos.length / this.registrosPorPagina);
    const inicio = (this.paginaActual - 1) * this.registrosPorPagina;
    const fin = inicio + this.registrosPorPagina;
    this.datosPaginados = datos.slice(inicio, fin);
  }

  cambiarPagina(nuevaPagina: number) {
    if (nuevaPagina >= 1 && nuevaPagina <= this.totalPaginas) {
      this.paginaActual = nuevaPagina;
      this.actualizarPaginacion(this.data);
    }
  }

  exportar(formato: 'csv' | 'txt' | 'xls') {
    this.service.exportarArchivo(this.data, formato);
  }

  accion(tipo: string, item: any) {
    console.log(`Acción ${tipo} sobre ID: ${item.ID}`);
  }
}