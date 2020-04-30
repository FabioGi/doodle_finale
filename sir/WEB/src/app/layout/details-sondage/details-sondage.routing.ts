
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailsSondageComponent } from './details-sondage.component';


const routes: Routes = [
    {
        path: '',
        component: DetailsSondageComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DetailsSondageRoutingModule {}
