import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { CompaniesService } from "./services/companies.service";
import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [CompaniesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
