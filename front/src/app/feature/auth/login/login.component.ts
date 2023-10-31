import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Login } from '../interface/login.interface';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/shared/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

  login$!: Subscription
  pseudo = new FormControl("");
  password = new FormControl("");
  loginFormGroup = new FormGroup({});
  loginUser! : Login;
  onError = false;
  errorMsg = "";

  constructor(private loginService: LoginService, private router : Router, private authService : AuthService) { }

  ngOnInit(): void {
    this.loginFormGroup.addControl('ID', this.pseudo )
    this.loginFormGroup.addControl('password', this.password )
    this.loginUser = {email : "", password: ""};
  }

  ngOnDestroy(): void {
    if (this.login$) 
      this.login$.unsubscribe();
  }

  onSubmit(){
    this.loginUser.email = this.pseudo.value!;
    this.loginUser.password = this.password.value!
    this.login$ = this.loginService.login(this.loginUser).subscribe({
      next : token => {
        this.onError = false;
        this.errorMsg = ""
        this.connect(token)},
      error: err => {
        this.onError = true;
        this.errorMsg= err.error.message
       }
    })
  }

  connect(jwt : {token : string}) : void {
    this.authService.login(jwt.token)
    this.router.navigate(['/post'])
  }
}
