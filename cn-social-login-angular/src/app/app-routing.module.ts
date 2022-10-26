import { NgModule } from '@angular/core';
import { provideProtractorTestingSupport } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProductosComponent } from './productos/productos.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'lista', component: ProductosComponent},
  {path: '***', redirectTo: '', pathMatch:'full'},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
