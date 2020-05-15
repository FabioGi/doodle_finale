import { RegisterComponent } from './register/register.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeComponent } from './employee/employee.component';
import { HttpClientModule, HTTP_INTERCEPTORS, HttpClient } from '@angular/common/http';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AngularMaterialModule } from './angular-material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BasicAuthHtppInterceptorService } from './service/basic-auth-interceptor.service';
import { SondageService } from './service/sondage.service';
import { DetailsSondageService } from './service/details-sondage.service';
import { AuthenticationService } from './service/authentication.service';
import { LayoutModule } from './layout/layout.module';
export const createTranslateLoader = (http: HttpClient) => {
  /* for development
  return new TranslateHttpLoader(
      http,
      '/start-javascript/sb-admin-material/master/dist/assets/i18n/',
      '.json'
  );*/
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
};

@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    AddEmployeeComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,

  ],
  imports: [
    BrowserModule,
    AngularMaterialModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    LayoutModule,
    TranslateModule.forRoot({
      loader: {
          provide: TranslateLoader,
          useFactory: createTranslateLoader,
          deps: [HttpClient]
      }
  })
  ],
  providers: [
    SondageService,
    DetailsSondageService,
    AuthenticationService,
     { provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }













// import { LayoutModule } from '@angular/cdk/layout';
// import { OverlayModule } from '@angular/cdk/overlay';
// import { NgModule } from '@angular/core';
// import {
//     MatButtonModule,
//     MatIconModule,
//     MatListModule,
//     MatSidenavModule,
//     MatToolbarModule
// } from '@angular/material';
// import { BrowserModule } from '@angular/platform-browser';
// import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// import { HttpClientModule, HttpClient } from '@angular/common/http';
// import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
// import { TranslateHttpLoader } from '@ngx-translate/http-loader';

// import { AppRoutingModule } from './app-routing.module';
// import { AppComponent } from './app.component';
// // AoT requires an exported function for factories
// export const createTranslateLoader = (http: HttpClient) => {
//     /* for development
//     return new TranslateHttpLoader(
//         http,
//         '/start-javascript/sb-admin-material/master/dist/assets/i18n/',
//         '.json'
//     );*/
//     return new TranslateHttpLoader(http, './assets/i18n/', '.json');
// };

// @NgModule({
//     declarations: [AppComponent],
//     imports: [
//         BrowserModule,
//         AppRoutingModule,
//         BrowserAnimationsModule,
//         LayoutModule,
//         OverlayModule,
//         HttpClientModule,
//         TranslateModule.forRoot({
//             loader: {
//                 provide: TranslateLoader,
//                 useFactory: createTranslateLoader,
//                 deps: [HttpClient]
//             }
//         })
//     ],
//     providers: [],
//     bootstrap: [AppComponent]
// })
// export class AppModule {}
