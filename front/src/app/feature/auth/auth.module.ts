import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';
import { CoreModule } from 'src/app/core/core.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { LoginService } from './service/login.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './register/register.component';
import { RegisterService } from './service/register.service';



@NgModule({
  declarations: [LoginComponent, RegisterComponent],
  imports: [
    CommonModule,
    CoreModule,
    AuthRoutingModule,
    SharedModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers :[LoginService, RegisterService]
})
export class AuthModule { }
