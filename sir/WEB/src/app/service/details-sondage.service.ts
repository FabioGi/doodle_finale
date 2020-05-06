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

  // getCreneauOrderBySondage(id){
  //   return this.httclient.get<any>(this.endpoint + '/creneaulist/' + id);
  // }

}
