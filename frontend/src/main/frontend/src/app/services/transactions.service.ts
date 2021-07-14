import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class TransactionsService {
  private url : string = 'http://localhost:4020/v1/transaction/';

  constructor(private httpClient : HttpClient) {  }

  public post(url: string, transaction: any){
    return this.httpClient.post(this.url + url, transaction);
  }
}
