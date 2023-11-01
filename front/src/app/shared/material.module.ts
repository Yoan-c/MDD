import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  exports :[
    MatToolbarModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    MatFormFieldModule
  ]
})
export class MaterialModule { }
