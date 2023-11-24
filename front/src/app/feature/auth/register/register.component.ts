import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { checkValidPassword } from '../validators/register.validator';
import { Register } from '../interface/register.inteface';
import { RegisterService } from '../service/register.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit, OnDestroy  {

  onError = false;
  errorMsg = '';
  register$!: Subscription;
  registerForm!: FormGroup;
  pseudo = new FormControl(this.registerForm, Validators.required);
  email = new FormControl(this.registerForm, [Validators.required, Validators.email]);
  password = new FormControl(
    this.registerForm,
    [
      Validators.required, Validators.minLength(8),
      checkValidPassword(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/)
    ]);
    registerUser: Register = {
      email: '',
      pseudo: '',
      password: ''
    };

  constructor(private registerService : RegisterService, private router : Router){}

  ngOnInit(): void {
    this.registerForm = new FormGroup({
      pseudo : this.pseudo,
      email : this.email,
      password : this.password
    });
  }

  ngOnDestroy(): void {
    if (this.register$)
      this.register$.unsubscribe();
  }

  onSubmit(): void {
    if (this.registerForm.status === "INVALID"){
      this.showError();
      return
    }
    this.errorMsg = "";
    this.onError = false;
    this.registerUser.email = this.email.value;
    this.registerUser.pseudo = this.pseudo.value;
    this.registerUser.password = this.password.value;
    this.register$ = this.registerService.register(this.registerUser).subscribe(
      {
        next : _ => {
          this.errorMsg="";
          this.onError = false;
          this.router.navigate(['/login']);
        },
        error : err => {
          this.errorMsg = `erreur : ${err.error.message}`;
          this.onError = true;
        }
      }
    )
  }

  showError(): void{
    this.onError = true;
    this.errorMsg = "erreur : ";
    const controls = this.registerForm.controls;
    if  (controls['pseudo'].errors)
      this.errorMsg += "Veuillez entrer un pseudo";
    else if  (controls['email'].errors)
      this.errorMsg += "Veuillez entrer un email valide";
    else if  (controls['password'].errors)
      this.errorMsg += "Le mot de passe doit contenir : <br> \
        - 8 caractères minimum <br>\
        - une minuscule <br>\
        - une majuscule <br>\
        - un chiffre <br> \
        - une caractère spécial (!@#$%^&*)";
  }

}
