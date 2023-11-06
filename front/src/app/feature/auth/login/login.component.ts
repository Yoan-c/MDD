import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup} from '@angular/forms';
import { Login } from '../interface/login.interface';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/shared/service/auth.service';
import { UserService } from 'src/app/shared/service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

  login$!: Subscription;
  pseudo = new FormControl("");
  password = new FormControl("");
  loginFormGroup = new FormGroup({});
  loginUser! : Login;
  errorMsg = "";

  constructor(
    private loginService: LoginService,
    private router : Router,
    private authService : AuthService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.loginFormGroup.addControl('ID', this.pseudo);
    this.loginFormGroup.addControl('password', this.password);
    this.loginUser = {email : "", password: ""};
  }

  ngOnDestroy(): void {
    if (this.login$) 
      this.login$.unsubscribe();
  }

  onSubmit(){
    if (this.loginFormGroup.status === 'INVALID'){
      this.errorMsg= "erreur : Vérifier les différents champs";
      return;
    }
    this.loginUser.email = this.pseudo.value!;
    this.loginUser.password = this.password.value!;
    this.login$ = this.loginService.login(this.loginUser).subscribe({
      next : token => {
        this.errorMsg = "";
        this.connect(token)},
      error: err => {
        this.errorMsg= err.error.message;
       }
    })
  }

  connect(jwt : {token : string}) : void {
    this.authService.login(jwt.token);
    this.userService.getMe().subscribe({
      next : token => this.router.navigate(['/post'])
    });
  }
}
