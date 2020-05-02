import { Injectable } from '@angular/core';
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

constructor(private httpClient: HttpClient) { }

}


register(){
  return this.httpClient
    .post<any>("http://localhost:8080/register", { username, password }, {inscriptionForm})
    .pipe(
      map(userData => {
        console.log(userData);
      })
    );
}
