import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { SondageDTO } from '../layout/sondage/sondageDTO';
import { AllergieDTO } from '../layout/reunion/allergieDTO';

@Injectable({
  providedIn: 'root'
})
export class ReunionService {
  endpoint = 'http://localhost:8080/api';

  constructor(private httpClient: HttpClient) { }

  getPreferences(id){
    return this.httpClient.get<any>('http://localhost:8080/api/preferences' + '/' + id );
  }

  getAllergie(id){
    return this.httpClient.get<any>('http://localhost:8080/api/allergies' + '/' + id );
  }

  deletePreferences(id) {
    return this.httpClient.delete<any>('http://localhost:8080/api/deletepreference' + '/' + id );
  }

  deleteAllergie(id) {
    return this.httpClient.delete<any>('http://localhost:8080/api/deleteallergie' + '/' + id );
  }

  addPreference(preference: AllergieDTO ){
   return this.httpClient
   .post<any>('http://localhost:8080/api/addpreference', preference);
  }

  addAllergie(allergie: AllergieDTO){
    return this.httpClient
    .post<any>('http://localhost:8080/api/addallergie', allergie);
   }

}
