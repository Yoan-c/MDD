import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { IndexComponent } from '../index/index.component';

const routes: Routes = [
  { path: '',  component : IndexComponent},
  { path: 'login',  component : LoginComponent},
  { path: 'register',  component : LoginComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
