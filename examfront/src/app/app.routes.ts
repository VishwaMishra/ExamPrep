import { RouterModule, Routes } from '@angular/router';
import { SignUpComponent } from './pages/sign-up/sign-up.component';
import { NgModule } from '@angular/core';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';

export const routes: Routes = [
    {
        path:'sign-up',
        component: SignUpComponent,
        pathMatch:'full'
    },
    {
        path:'login',
        component: LoginComponent,
        pathMatch:'full'
    },
    {
        path:'',
        component: HomeComponent,
        pathMatch:'full'
    }

];

@NgModule({
    imports:[RouterModule.forRoot(routes)],
    exports:[RouterModule]
})

export class AppRoutingModule {}
