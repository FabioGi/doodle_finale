import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { NgxMatDatetimePickerModule, NgxMatTimepickerModule, NgxMatNativeDateModule } from '@angular-material-components/datetime-picker';
import { DetailsSondageComponent } from './details-sondage.component';
import { DetailsSondageRoutingModule } from './details-sondage.routing';
import { FlexLayoutModule } from '@angular/flex-layout';
// import {MatDatepickerModule} from '@angular/material/datepicker';


@NgModule({
  imports: [
    CommonModule,
    DetailsSondageRoutingModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    NgxMatDatetimePickerModule,
    NgxMatTimepickerModule,
    NgxMatNativeDateModule,
    FlexLayoutModule.withConfig({addFlexToParent: false})
  ],
  providers: [
    // NgxMatDatetimePickerModule,
    // NgxMatTimepickerModule
    NgxMatNativeDateModule
  ],
  declarations: [DetailsSondageComponent]
})
export class DetailsSondageModule { }




