import { Component } from '@angular/core';
import {CompaniesService, Company} from './services/companies.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular2boot';
  companies : Company[];

  constructor(private companiesService : CompaniesService) {
    this.companies = [];
    this.getCompanies();
  }
  public getCompanies(){
    this.companiesService.getAllCompanies()
      .subscribe(
        value => {
          this.companies = value;
          console.log(this.companies[0].name);
        },
        error => {
          console.log(error);
        }
      )
  }
}
