import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatComponent } from './stat.component';
// import { MatCardModule } from '@angular/material';
import { AngularMaterialModule } from 'src/app/angular-material.module';
import { RouterModule } from '@angular/router';
// import { MatGridListModule, MatIconModule } from '@angular/material';

@NgModule({
    imports: [CommonModule, RouterModule, AngularMaterialModule],
    declarations: [StatComponent],
    exports: [StatComponent]
})
export class StatModule {}
