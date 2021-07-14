import { Component } from '@angular/core';
import {CompaniesService, Company} from './services/companies.service';
import {TransactionsService} from './services/transactions.service';
import {HttpErrorResponse} from '@angular/common/http';

export enum Error{
  PARSE_ERROR = "Incorrect input!",
  VALUE_ERROR = "Transaction value bigger then company's balance!"
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular2boot';
  companies : Company[];
  transactionValues : string[];
  errorText : Error = Error.VALUE_ERROR;

  constructor(private companiesService : CompaniesService, private transactionsService : TransactionsService) {
    this.companies = [];
    this.transactionValues = [];
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
      this.post(index,'outgoing');
  }
  private post(index : number, endpoint : string){
    this.setErrorVisible(false);
    let company = this.companies[index];
    let transactionValue : number = Number(this.transactionValues[index]);
    if(isNaN(transactionValue)){
      this.errorText = Error.PARSE_ERROR;
      this.setErrorVisible(true);
      return;
    }
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
            this.errorText = Error.VALUE_ERROR;
            this.setErrorVisible(true);
          }
          //500 == InternalServerError, throws when another transactions blocks
          if(error.status == 500){
              this.post(index, endpoint);
          }
        }
      )
  }
  private setErrorVisible(status : boolean){
    let message = document.getElementById('message');
    if(message != null){
      message.hidden = !status;
    }
  }
}

