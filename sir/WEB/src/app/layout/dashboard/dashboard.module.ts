import { AngularMaterialModule } from './../../angular-material.module';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ChartsModule as Ng2Charts } from 'ng2-charts';
// import { MatButtonModule, MatCardModule, MatIconModule, MatTableModule } from '@angular/material';
// import { MatGridListModule } from '@angular/material/grid-list';

import { StatModule } from '../../shared/modules/stat/stat.module';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';

@NgModule({
    imports: [
        CommonModule,
        DashboardRoutingModule,
        // MatGridListModule,
        StatModule,
        Ng2Charts,
        // MatCardModule,
        // MatCardModule,
        // MatTableModule,
        // MatButtonModule,
        // MatIconModule,
        FlexLayoutModule.withConfig({addFlexToParent: false}),
        AngularMaterialModule
    ],
    declarations: [DashboardComponent]
})
export class DashboardModule {}
