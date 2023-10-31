import { Injectable } from "@angular/core";
import { Router } from "@angular/router";

@Injectable({
    providedIn : 'root',
})
export class ErrorApp {
    constructor(private router : Router){}

    checkError(err : any){
        if (err.status === 401)
            this.redirect('/login')
    }

    redirect(url : string) {
        this.router.navigate(['/'   ])
    }
}