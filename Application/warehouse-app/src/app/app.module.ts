import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'; 
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent }, 
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    LoginComponent 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
