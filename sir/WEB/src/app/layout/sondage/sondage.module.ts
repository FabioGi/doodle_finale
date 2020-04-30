import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SondageComponent } from './sondage.component';
import { SondageRoutingModule } from './sondage.module.routing';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { NgxMatDatetimePickerModule, NgxMatTimepickerModule, NgxMatNativeDateModule } from '@angular-material-components/datetime-picker';
// import {MatDatepickerModule} from '@angular/material/datepicker';


@NgModule({
  imports: [
    CommonModule,
    SondageRoutingModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgxMatDatetimePickerModule,
    NgxMatTimepickerModule,
    NgxMatNativeDateModule
  ],
  providers: [
    // NgxMatDatetimePickerModule,
    // NgxMatTimepickerModule
    NgxMatNativeDateModule
  ],
  declarations: [SondageComponent]
})
export class SondageModule { }




