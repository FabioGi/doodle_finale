import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SondageService {
  endpoint = 'http://localhost:8080/api';
  username = 'fabio';
  password = 'fabio';
  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };


  getUsersMail() {
  //  const headers = new HttpHeaders({ Authorization: 'Bearer ' +  btoa(this.username + ':' + this.password) });
    return this.httpClient.get<any>(this.endpoint + '/users').pipe(
        map((users) => {
            return users;
        })
      );
  }

  // register(data) {
  //   return this.httpClient
  //     .post<InscriptionForm>('http://localhost:8080/register', data);
  // }

}
