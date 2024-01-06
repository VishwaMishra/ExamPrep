import { RouterModule, Routes } from '@angular/router';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { NgModule } from '@angular/core';

export const routes: Routes = [
    {
        path:'sign-up',
        component: SignUpComponent,
        pathMatch:'full'
    }
];

@NgModule({
    imports:[RouterModule.forRoot(routes)],
    exports:[RouterModule]
})

export class AppRoutingModule {}
