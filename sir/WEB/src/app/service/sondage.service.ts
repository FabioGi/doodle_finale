import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SondageService {

  constructor(private httpClient: HttpClient) { }

  getUsersMail() {
      return this.httpClient.get<any>('http://localhost:8080/api/users').pipe(
        map((users) => {
            return users.email;
        })
      );
  }

  // register(data) {
  //   return this.httpClient
  //     .post<InscriptionForm>('http://localhost:8080/register', data);
  // }

}
