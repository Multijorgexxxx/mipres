import { Component, OnInit, inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { MenuService } from '../../services/menu/menu.service';
import { MenuItem } from '../../models/menu.model';
import { ActivatedRoute } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './menu.html',
  styleUrls: ['./menu.css']
})
export class MenuComponent implements OnInit {
  menuItems: MenuItem[] = [];
  menuActivo: string = '';
  username: string = 'Usuario';

  private platformId = inject(PLATFORM_ID);

  constructor(
    private menuService: MenuService,
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef
  ) {}

ngOnInit(): void {
  this.route.data.subscribe(({ menus }) => {
    console.log('DATOS DEL RESOLVER:', menus);
    if (isPlatformBrowser(this.platformId)) {
      const storedName = localStorage.getItem('user_name');
      if (storedName) {
        this.username = storedName;
      }
    }

    if (menus && menus.length > 0) {
      this.menuItems = menus.map((m: any) => ({ ...m, expandido: false }));
      
      this.cdr.detectChanges(); 
      
      this.verificarRutaActiva();
    }
  });
}

  cargarMenus(): void {
    this.menuService.cargarMenus(1).subscribe(menus => {
      this.menuItems = this.inicializarExpandido(menus);
      this.verificarRutaActiva();
    });
  }

  private inicializarExpandido(menus: MenuItem[]): MenuItem[] {
    return menus.map(m => ({
      ...m,
      expandido: false,
      submenus: m.submenus ? this.inicializarExpandido(m.submenus) : []
    }));
  }

  verificarRutaActiva(): void {
    this.menuActivo = this.encontrarRutaActiva(this.menuItems, this.router.url);
  }

  private encontrarRutaActiva(menus: MenuItem[], urlActual: string): string {
    for (const menu of menus) {
      if (menu.ruta && urlActual.includes(menu.ruta) && menu.ruta !== '#') {
        return menu.ruta;
      }
      if (menu.submenus?.length) {
        const encontrada = this.encontrarRutaActiva(menu.submenus, urlActual);
        if (encontrada) {
          menu.expandido = true;
          return encontrada;
        }
      }
    }
    return '';
  }

  toggleSubmenu(item: MenuItem, event: Event): void {
    if (item.submenus && item.submenus.length > 0) {
      event.preventDefault();
      event.stopPropagation();
      item.expandido = !item.expandido;

      this.menuItems.forEach(m => {
        if (m !== item) m.expandido = false;
      });
    }
  }

  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('user_id');
      localStorage.removeItem('user_name');
      localStorage.removeItem('user_role');
    }
    this.router.navigate(['/login']);
  }
}