import { Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {ProfilePageComponent} from "./profile-page/profile-page.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {UnauthorizedComponent} from "./unauthorized/unauthorized.component";
import {HomeComponent} from "./home/home.component";

export const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "home", component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfilePageComponent},
  {path: 'pageNotFound', component: PageNotFoundComponent},
  {path: 'unauthorized', component: UnauthorizedComponent}
];
