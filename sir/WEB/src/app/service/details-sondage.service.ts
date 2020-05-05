import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DetailsSondageService {
endpoint = 'http:/mail/localhost:8080/api';
constructor(private httclient: HttpClient) { }

  getSondageDetails(id) {
      return this.httclient.get<any>(this.endpoint + '/sondage/' + id);
  }

  getInvitationListOrderBySondage(id){
    return this.httclient.get<any>(this.endpoint + '/invitation/' + id);
  }

  getCreneauOrderBySondage(id){
    return this.httclient.get<any>(this.endpoint + '/creneaulist/' + id);
  }

}
