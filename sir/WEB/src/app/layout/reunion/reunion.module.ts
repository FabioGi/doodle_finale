import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { ReunionComponent, AlimentaireDialog } from './reunion.component';
import { ReunionRoutingModule } from './reunion.routing';
import { FlexLayoutModule } from '@angular/flex-layout';
import { NgxQRCodeModule } from 'ngx-qrcode2';
// import {MatDatepickerModule} from '@angular/material/datepicker';


@NgModule({
  imports: [
    CommonModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    ReunionRoutingModule,
    NgxQRCodeModule,
    FlexLayoutModule.withConfig({addFlexToParent: false}),
  ],
  providers: [
  ],
  entryComponents: [AlimentaireDialog],
  declarations: [ReunionComponent,AlimentaireDialog]
})
export class ReunionModule { }




