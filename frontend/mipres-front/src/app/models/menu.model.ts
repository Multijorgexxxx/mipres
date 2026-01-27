export interface MenuItem {
  id: number;
  titulo: string;      // Coincide con tu API
  ruta: string;        // Coincide con tu API
  icono: string;
  menuPadreId: number;
  activo: boolean;
  orden: number;
  idRol: number;
  submenus?: MenuItem[];
  expandido?: boolean; // Para manejo de UI
}