import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login';
import { MenuComponent } from './components/menu/menu';
import { menuResolver } from './components/menu/menu.resolver';
import { ProgramarComponent } from './components/programar/programar'; // Ajusta la ruta a tu archivo real

export const routes: Routes = [
  { 
    path: 'login', 
    component: LoginComponent 
  },
  { 
    path: 'home', 
    component: MenuComponent, 
    resolve: { menus: menuResolver },
    children: [
      { 
        path: 'programar', 
        component: ProgramarComponent 
      },
      /*{ 
        path: '', 
        redirectTo: 'programar',
        pathMatch: 'full' 
      }*/
    ]
  },
  { 
    path: '', 
    redirectTo: 'login', 
    pathMatch: 'full' 
  },
  {
    path: '**',
    redirectTo: 'login'
  }
];