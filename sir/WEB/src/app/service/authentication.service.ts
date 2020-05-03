import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/operators";

export class User {
  constructor(public status: string) {}
}

@Injectable({
  providedIn: "root"
})
export class AuthenticationService {
  constructor(private httpClient: HttpClient) {}
// Provide username and password for authentication, and once authentication is successful,
//store JWT token in session
  authenticate(username, password) {
    return this.httpClient
      .post<any>("http://localhost:8080/authenticate", { username, password })
      .pipe(
        map(userData => {
          sessionStorage.setItem("username", username);
          sessionStorage.setItem("email", userData.user.email);
          let tokenStr = "Bearer " + userData.token;
          sessionStorage.setItem("token", tokenStr);
       //   console.log(userData);
          return userData;
        })
      );
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem("username");
    let userMail = sessionStorage.getItem("email");
    console.log(!(user === null));
   // console.log(userMail);
    return !(user === null);
  }

  logOut() {
    sessionStorage.removeItem("username");
  }

  // getUser(username) {
  //     return this.httpClient.get<any>('http://localhost:8080/api/user/'+ username);
  // }

}
