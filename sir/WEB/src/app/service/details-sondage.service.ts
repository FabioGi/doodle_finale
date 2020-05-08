import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Vote } from '../layout/details-sondage/Vote';

@Injectable({
  providedIn: 'root'
})
export class DetailsSondageService {
endpoint = 'http://localhost:8080/api';
constructor(private httpClient: HttpClient) { }

  getSondageDetails(id) {
      return this.httpClient.get<any>(this.endpoint + '/sondage/' + id);
  }

  getInvitationListOrderBySondage(id) {
    return this.httpClient.get<any>(this.endpoint + '/invitation/' + id);
  }

  // choseDateToMeeting(vote) {
  //   return this.httpClient.
  //      post<any>(this.endpoint + '/chosecreneau', vote);
  // }

  choseDateToMeeting(vote) {
    return this.httpClient
       .post<any>('http://localhost:8080/api/chosecreneau', vote);
  }

  countSlotOrderByUser(slot,survey){
    return this.httpClient.get<any>('http://localhost:8080/api/countslot/' + slot + '/' + survey );
  }

  getUserChoice(idSlot,email){
      return this.httpClient.get<number[]>('http://localhost:8080/api/responses/' + email + '/' + idSlot );
  }

}
