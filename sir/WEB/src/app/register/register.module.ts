import { AngularMaterialModule } from './../angular-material.module';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserModule } from '@angular/platform-browser';
// import { MatButtonModule, MatCheckboxModule, MatInputModule } from '@angular/material';

import { RegisterRoutingModule } from './register.routing.module';
import { RegisterComponent } from './register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
    imports: [
        CommonModule,
        RegisterRoutingModule,
        BrowserModule,
        AngularMaterialModule,
        FormsModule,
        ReactiveFormsModule,
        FlexLayoutModule.withConfig({addFlexToParent: false})
    ],
    declarations: [RegisterComponent]
})
export class RegisterModule {}
