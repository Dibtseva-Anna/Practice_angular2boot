import { Component } from '@angular/core';
import {CompaniesService, Company} from './services/companies.service';
import {TransactionsService} from "./services/transactions.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular2boot';
  companies : Company[];

  constructor(private companiesService : CompaniesService, private transactionsService : TransactionsService) {
    this.companies = [];
    this.loadCompanies();
  }
  public loadCompanies(){
    this.companiesService.getAllCompanies()
      .subscribe(
        value => {
          this.companies = value;
        },
        error => {
          console.log(error);
        }
      )
  }
  public postIncome(index : number){
    this.post(index, 'income');
  }
  public postOutcome(index : number){
    this.post(index, 'outcome');
  }
  private post(index : number, endpoint : string){
    let company = this.companies[index];
    let transactionValue = 200;
    this.transactionsService.post(endpoint, {company, transactionValue})
      .subscribe(
        (data : any) => {
          this.companies[index].balance += data.transactionValue;
          console.log(data);
        },
        error => {
          console.log(error);
        }
      )
  }
}
