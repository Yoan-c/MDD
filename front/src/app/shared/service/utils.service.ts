import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root',
})
export class UtilsService {
    isPasswordValid(password : string) : boolean{
       let regex = new RegExp(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/)
       console.log(regex.test(password));
       
       return regex.test(password)
    }
}