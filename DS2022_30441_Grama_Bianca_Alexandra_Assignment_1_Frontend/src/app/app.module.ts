import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {LoginComponent} from "./content/pages/login/login.component";
import {CheckboxModule} from "primeng/checkbox";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from 'primeng/inputtext';
import {StyleClassModule} from "primeng/styleclass";
import {FormsModule} from "@angular/forms";
import {AuthguardClientService} from "./services/authguard-client.service";
import {HttpClientModule} from "@angular/common/http";
import {MenubarModule} from "primeng/menubar";
import {DividerModule} from "primeng/divider";
import {CardModule} from "primeng/card";
import {InputTextareaModule} from "primeng/inputtextarea";
import {DialogModule} from "primeng/dialog";
import {EditorModule} from "primeng/editor";
import {HeaderClientComponent} from "./content/global/header-client/header-client.component";
import {HeaderAdminComponent} from "./content/global/header-admin/header-admin.component";
import {SignupComponent} from "./content/pages/signup/signup.component";
import {PersonsComponent} from "./content/pages/admin/users/persons.component";
import {DevicesComponent} from "./content/pages/admin/devices/devices.component";
import {MappingComponent} from "./content/pages/admin/mapping/mapping.component";
import {ListboxModule} from "primeng/listbox";
import {MyDevicesComponent} from "./content/pages/client/my-devices/my-devices.component";
import {ChartsComponent} from "./content/pages/client/charts/charts.component";
import {ChartModule} from "primeng/chart";
import {CalendarModule} from 'primeng/calendar';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AuthguardAdminService} from "./services/authguard-admin.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderClientComponent,
    HeaderAdminComponent,
    SignupComponent,
    PersonsComponent,
    DevicesComponent,
    MappingComponent,
    MyDevicesComponent,
    ChartsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CheckboxModule,
    FormsModule,
    HttpClientModule,
    ButtonModule,
    InputTextModule,
    StyleClassModule,
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    MenubarModule,
    DividerModule,
    CardModule,
    InputTextareaModule,
    EditorModule,
    DialogModule,
    ListboxModule,
    ChartModule,
    CalendarModule,
    BrowserAnimationsModule
  ],
  providers: [AuthguardClientService, AuthguardAdminService],
  bootstrap: [AppComponent]
})
export class AppModule { }
