import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

export interface Company {
  id : number;
  name : string;
  balance : number;
}

@Injectable()
export class CompaniesService {
  private url : string = 'http://localhost:4020/api/balance/';

  constructor(private httpClient : HttpClient) {  }

  public getAllCompanies(){
    return this.httpClient.get<Company[]>(this.url);
  }
}
