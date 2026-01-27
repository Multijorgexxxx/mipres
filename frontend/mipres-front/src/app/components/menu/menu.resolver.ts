import { inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { ResolveFn } from '@angular/router';
import { MenuService } from '../../services/menu/menu.service';
import { MenuItem } from '../../models/menu.model';
import { of } from 'rxjs';

export const menuResolver: ResolveFn<MenuItem[]> = (route, state) => {
  const menuService = inject(MenuService);
  const platformId = inject(PLATFORM_ID);

  let idRol = 0;

  if (isPlatformBrowser(platformId)) {
    idRol = Number(localStorage.getItem('user_role') || '0');
  }

  console.log('Resolver ejecutándose en:', isPlatformBrowser(platformId) ? 'Navegador' : 'Servidor');
  return menuService.cargarMenus(idRol);
};