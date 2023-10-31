import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function checkValidPassword(regPassword : RegExp) : ValidatorFn {
    return (control: AbstractControl) : ValidationErrors | null => {
        const failed = regPassword.test(control.value)
        return !failed ? {checkPassword : {value: control.value}} : null;
    }
}

export function checkValidEmail(regEmail : RegExp) : ValidatorFn {
    return (control: AbstractControl) : ValidationErrors | null => {
        const failed = regEmail.test(control.value)
        return failed ? {checkEmail : {value: control.value}} : null;
    }
}