import { Component } from '@angular/core';
import {CompaniesService, Company} from './services/companies.service';
import {TransactionsService} from "./services/transactions.service";
import {HttpErrorResponse} from "@angular/common/http";

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
  public postIncoming(index : number){
      this.post(index, 'incoming');
  }
  public postOutgoing(index : number){
     //for (let i : number = 0; i < 10; i++)
      this.post(index, 'outgoing');
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
        (error : HttpErrorResponse) => {
          console.log(error);
          //422 == UnprocessableEntity, throws when transaction value > company's balance
          if(error.status == 422){

          }
          //500 == InternalServerError, throws when another transactions blocks
          if(error.status == 500){
              this.post(index, endpoint);
          }
        }
      )
  }
}
