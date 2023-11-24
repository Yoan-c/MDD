import { Injectable } from "@angular/core";
import { Router } from "@angular/router";

@Injectable({
    providedIn : 'root',
})
export class ErrorApp {
    constructor(private router : Router){}

    checkError(err : any): void{
        if (err.status === 401)
            this.redirect('/login');
    }

    redirect(url : string): void {
        this.router.navigate(['/']);
    }
}