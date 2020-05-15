import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LayoutComponent } from './layout.component';
import { AuthGaurdService } from '../service/auth-gaurd.service';

const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            {
              path: 'sondage',
              loadChildren: './sondage/sondage.module#SondageModule', canActivate: [AuthGaurdService]
            },
            {
              path: 'sondage-details/:id',
              loadChildren: './details-sondage/details-sondage.module#DetailsSondageModule', canActivate: [AuthGaurdService]
            },
            {
              path: 'reunion/:id',
              loadChildren: './reunion/reunion.module#ReunionModule', canActivate: [AuthGaurdService]
            },
            {
                path: 'dashboard',
                loadChildren: './dashboard/dashboard.module#DashboardModule', canActivate: [AuthGaurdService]
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
