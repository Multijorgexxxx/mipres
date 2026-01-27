import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { MenuComponent } from './components/menu/menu';
import { MenuService } from './services/menu/menu.service';

@NgModule({
  declarations: [
    // Eliminamos AppComponent y MenuComponent de aquí
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    RouterModule.forRoot([]), // Configura tus rutas aquí
    // Añadimos los componentes standalone aquí
    AppComponent,
    MenuComponent
  ],
  providers: [MenuService],
  bootstrap: [AppComponent]
})
export class AppModule { }