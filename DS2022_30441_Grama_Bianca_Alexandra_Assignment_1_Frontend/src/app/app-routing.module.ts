import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./content/pages/login/login.component";
import {SignupComponent} from "./content/pages/signup/signup.component";
import {PersonsComponent} from "./content/pages/admin/users/persons.component";
import {AuthguardClientService} from "./services/authguard-client.service";
import {DevicesComponent} from "./content/pages/admin/devices/devices.component";
import {MappingComponent} from "./content/pages/admin/mapping/mapping.component";
import {MyDevicesComponent} from "./content/pages/client/my-devices/my-devices.component";
import {ChartsComponent} from "./content/pages/client/charts/charts.component";
import {AuthguardAdminService} from "./services/authguard-admin.service";

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'persons', component: PersonsComponent, canActivate: [AuthguardAdminService]},
  {path: 'devices', component: DevicesComponent, canActivate: [AuthguardAdminService]},
  {path: 'mapping', component: MappingComponent, canActivate: [AuthguardAdminService]},
  {path: 'my-devices', component: MyDevicesComponent, canActivate: [AuthguardClientService]},
  {path: 'charts', component: ChartsComponent, canActivate: [AuthguardClientService]}
  /*{
    path: 'persons', component: PersonsComponent, canActivate: [AuthguardAdminService],
    children: [
      {path: '', redirectTo: 'login', pathMatch: 'full'},
      {path: 'persons', component: PersonsComponent, canActivate: [AuthguardAdminService]}
    ]
  },
  {
    path: 'devices', component: DevicesComponent, canActivate: [AuthguardAdminService],
    children: [
      {path: '', redirectTo: 'login', pathMatch: 'full'},
      {path: 'persons', component: DevicesComponent, canActivate: [AuthguardAdminService]}
    ]
  },
  {
    path: 'mapping', component: MappingComponent, canActivate: [AuthguardAdminService],
    children: [
      {path: '', redirectTo: 'login', pathMatch: 'full'},
      {path: 'mapping', component: MappingComponent, canActivate: [AuthguardAdminService]}
    ]
  },
  {
    path: 'my-devices', component: MyDevicesComponent, canActivate: [AuthguardClientService],
    children: [
      {path: '', redirectTo: 'login', pathMatch: 'full'},
      {path: 'my-devices', component: MyDevicesComponent, canActivate: [AuthguardClientService]}
    ]
  },
  {
    path: 'charts', component: ChartsComponent, canActivate: [AuthguardClientService],
    children: [
      {path: '', redirectTo: 'login', pathMatch: 'full'},
      {path: 'charts', component: ChartsComponent, canActivate: [AuthguardClientService]}
    ]
  }*/
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
