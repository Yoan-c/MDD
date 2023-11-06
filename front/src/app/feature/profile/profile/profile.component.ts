import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription, mergeMap } from 'rxjs';
import { Topic } from 'src/app/shared/interface/topic.interface';
import { User } from 'src/app/shared/interface/user.interface';
import { AuthService } from 'src/app/shared/service/auth.service';
import { UserService } from 'src/app/shared/service/user.service';
import { UtilsService } from 'src/app/shared/service/utils.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit , OnDestroy {

  auth$!: Subscription;
  user$!: Subscription;
  unSubTopics$!: Subscription;
  topics!: Topic[];
  user!: User;
  profileFormGroup = new FormGroup({});
  pseudo! : FormControl;
  email! : FormControl;
  password! : FormControl;
  confirmPassword! : FormControl;
  errorMsg: string = "";
  successMsg: string = "";

  constructor(
    private authService: AuthService,
    private userService : UserService,
    private utilsService: UtilsService,
    private router: Router
  ){}
  
  ngOnInit(): void {
    this.user$ = this.userService.user$.pipe(
      mergeMap(user => {
        this.user = user;
        this.pseudo = new FormControl(this.user.pseudo, Validators.required);
        this.email = new FormControl(this.user.email, [Validators.required, Validators.email]);
        this.password = new FormControl("");
        this.confirmPassword = new FormControl("");
        this.profileFormGroup.addControl("pseudo", this.pseudo);
        this.profileFormGroup.addControl("email", this.email);
        this.profileFormGroup.addControl("password", this.password);
        this.profileFormGroup.addControl("confirmPassword", this.confirmPassword);
        return this.userService.getAllTopicByUser();
      })
    ).subscribe({
      next : topics => this.topics = topics
    });
  }

  ngOnDestroy(): void {
      this.user$.unsubscribe();
      if (this.auth$)
        this.auth$.unsubscribe();
      if (this.unSubTopics$)
      this.unSubTopics$.unsubscribe();
  }

  logout(){
    this.auth$ = this.authService.logout().subscribe();
    this.router.navigate(['/']);
  }

  onSubmit() {
    if (this.profileFormGroup.status === "INVALID")
      return;
    if (this.checkInvalidPassword())
      return ;
    
    this.userService.updateMe(
      this.pseudo.value,
      this.email.value,
      this.password.value,
      this.confirmPassword.value
      ).pipe(
      mergeMap((jwt : {token : string}) => {
        this.authService.setToken(jwt.token);
        return this.userService.getMe();
      })
    ).subscribe(
      {
        next : user => { this.user = user;
          this.showSuccessMsg();
        },
        error : err => this.showErrorMsg(err)
      }
    )
  }

  checkInvalidPassword() {
    if (this.password.value === "" && this.confirmPassword.value === "")
      return false;
    if (this.password.value === this.confirmPassword.value && this.utilsService.isPasswordValid(this.password.value))
      return false;
    this.successMsg = ""
    this.errorMsg = "les mots de passes doivent :<br>\
    - être identique<br>\
    - avoir au minimum 8 caractères<br>\
    - avoir un caractère spécial (!@#$%^&*) <br>\
    - avoir un chiffre<br>\
    - avoir une majuscule<br>\
    - avoir une minuscule";
    return true;
  }

  showSuccessMsg() {
    this.successMsg = "Utilisateur modifié";
    this.errorMsg = "";
  }
  showErrorMsg(err: { error: { message: string; }; }) {
    this.successMsg = "";
    this.errorMsg = err.error.message;
  }

  unSubscribeUser(id : string) : void {
    this.unSubTopics$ = this.userService.unSubscribeUser(id).pipe(
      mergeMap(() => {
        this.removeTopic(id);
        return this.userService.getMe();
      })
    ).subscribe();
  }

  removeTopic(id : string) {
    this.topics = this.topics.filter(topic => topic.id !== id);
  }
}
