import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SondageComponent } from './sondage.component';


const routes: Routes = [
    {
        path: '',
        component: SondageComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SondageRoutingModule {}
