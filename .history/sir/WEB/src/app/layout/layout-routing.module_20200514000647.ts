import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LayoutComponent } from './layout.component';
import { AuthGaurdService } from '../service/auth-gaurd.service';

const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            // {
            //     path: '',
            //     redirectTo: 'dashboard'
            // },
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
            // {
            //   path: 'sondages',
            //   loadChildren: './sondages/sondages.module#SondagesModule'
            // },
            {
                path: 'dashboard',
                loadChildren: './dashboard/dashboard.module#DashboardModule', canActivate: [AuthGaurdService]
            },
            {
                path: 'charts',
                loadChildren: './charts/charts.module#ChartsModule'
            },
            {
                path: 'components',
                loadChildren:
                    './material-components/material-components.module#MaterialComponentsModule', canActivate: [AuthGaurdService]
            },
            {
                path: 'forms',
                loadChildren: './forms/forms.module#FormsModule'
            },
            {
                path: 'grid',
                loadChildren: './grid/grid.module#GridModule'
            },
            {
                path: 'tables',
                loadChildren: './tables/tables.module#TablesModule'
            },
            {
                path: 'blank-page',
                loadChildren: './blank-page/blank-page.module#BlankPageModule'
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
