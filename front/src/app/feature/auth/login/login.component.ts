import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { login } from '../interface/login.interface';
import { LoginService } from '../service/login.service';
import { catchError, of, tap, throwError } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {


  id = new FormControl("");
  password = new FormControl("");
  loginFormGroup = new FormGroup({});
  login! : login;

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginFormGroup.addControl('ID', this.id )
    this.loginFormGroup.addControl('password', this.password )
    this.login = {email : "", password: ""};
    
  }

  onSubmit(){
    this.login.email = this.id.value!;
    this.login.password = this.password.value!
    this.loginService.login(this.login).subscribe(
      err => console.log("err"+err),
      res => console.log(res)
    )
  }
}
