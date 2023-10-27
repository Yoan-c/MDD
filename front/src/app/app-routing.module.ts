import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
//const routes: Routes = [{ path: '', component: HomeComponent }];

@NgModule({
  imports: [/*RouterModule.forRoot(routes)*/],
  exports: [RouterModule],
})
export class AppRoutingModule {}
