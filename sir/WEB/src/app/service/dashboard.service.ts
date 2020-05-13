import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DashBoardService {
endpoint = 'http://localhost:8080/api';
constructor(private httpClient: HttpClient) { }

  getSurveyList(id) {
      return this.httpClient.get<any>(this.endpoint + '/surveylist/' + id);
  }

  getSurveyListCreatedOrderByUser(id) {
    return this.httpClient.get<any>(this.endpoint + '/surveylistcreated/' + id);
}

countSurveyValided(email) {
  return this.httpClient.get<any>(this.endpoint + '/countsurveyvalided/' + email );
}

}
