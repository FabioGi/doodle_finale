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
import { StatutComponent } from './statut/statut.component';
import { StatutCreatorComponent } from './statut-creator/statut-creator.component';
import { StatistiqueComponent } from './statistique/stat.component';

@NgModule({
    imports: [
        CommonModule,
        DashboardRoutingModule,
        // MatGridListModule,
        StatModule,
        Ng2Charts,
        // MatCardModule,
        FlexLayoutModule.withConfig({addFlexToParent: false}),
        AngularMaterialModule
    ],
    declarations: [DashboardComponent, StatutComponent, StatutCreatorComponent, StatistiqueComponent]
})
export class DashboardModule {}
