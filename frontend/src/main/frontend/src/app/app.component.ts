import { Component } from '@angular/core';
import {CompaniesService, Company} from './services/companies.service';
import {TransactionsService} from './services/transactions.service';
import {HttpErrorResponse} from '@angular/common/http';

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
  public postIncoming(index : number, transactionValue : number){
      this.post(index, transactionValue, 'incoming');
  }
  public postOutgoing(index : number, transactionValue : number){
      this.post(index, transactionValue, 'outgoing');
  }
  private post(index : number, transactionValue : number, endpoint : string){
    let company = this.companies[index];
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
            let message = document.getElementById('message');
            if(message != null)
              message.hidden = false;
          }
          //500 == InternalServerError, throws when another transactions blocks
          if(error.status == 500){
              this.post(index, endpoint);
          }
        }
      )
  }
}
