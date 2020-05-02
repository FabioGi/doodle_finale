
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/operators";
import { InscriptionForm } from '../register/InscriptionForm';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

constructor(private httpClient: HttpClient) { }

  register(data) {
    return this.httpClient
      .post<InscriptionForm>('http://localhost:8080/register', data);
  }

}

