import { filter } from 'rxjs/operators';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { DashBoardService } from 'src/app/service/dashboard.service';
import {MatPaginator} from '@angular/material/paginator';
import { Sondage } from '../sondage/sondage';

// export interface PeriodicElement {
//     name: string;
//     position: number;
//     weight: number;
//     symbol: string;
// }

// const ELEMENT_DATA: PeriodicElement[] = [
//   {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
//   {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
//   {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
//   {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
//   {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
//   {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
//   {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
//   {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
//   {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
//   {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
//   {position: 11, name: 'Sodium', weight: 22.9897, symbol: 'Na'},
//   {position: 12, name: 'Magnesium', weight: 24.305, symbol: 'Mg'},
//   {position: 13, name: 'Aluminum', weight: 26.9815, symbol: 'Al'},
//   {position: 14, name: 'Silicon', weight: 28.0855, symbol: 'Si'},
//   {position: 15, name: 'Phosphorus', weight: 30.9738, symbol: 'P'},
//   {position: 16, name: 'Sulfur', weight: 32.065, symbol: 'S'},
//   {position: 17, name: 'Chlorine', weight: 35.453, symbol: 'Cl'},
//   {position: 18, name: 'Argon', weight: 39.948, symbol: 'Ar'},
//   {position: 19, name: 'Potassium', weight: 39.0983, symbol: 'K'},
//   {position: 20, name: 'Calcium', weight: 40.078, symbol: 'Ca'},
// ];


@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  displayedColumns: string[] = ['titre', 'lieu', 'statut', 'action'];
  displayedColumns2: string[] = ['titre', 'lieu', 'statut', 'hôte', 'action'];
  dataSource: MatTableDataSource<Sondage>;
  dataSource2: MatTableDataSource<Sondage>;
   // = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatPaginator, {static: true}) paginator2: MatPaginator;

    // places: Array<any> = [];
    // panelOpenState = false;
    //  public pieChartLabels: string[] = ['Download Sales', 'In-Store Sales', 'Mail Sales','Sales'];
    //  public pieChartData: number[] = [300, 500, 100,100];
    //  public pieChartType: string;
  surveyList: any;
  userSurveyList: any;
  userSurveyTotal: any;
  surveyListTotal: any;
  userSurveyValided: any;
  surveyValided: any;
  surveyNoValided: any;
  surveyListValided: any;
  invitationValided = 0;
  invitationNoValided = 0;
  meetingIscreated: any;

    constructor(private ds: DashBoardService) {
    }

    ngOnInit() {
      // const dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

      const currentUser = sessionStorage.getItem('email');
      this.ds.getSurveyList(currentUser).subscribe((data)=>{
          this.surveyList = data;
          this.surveyListTotal = data? data.length:0;
          // this.surveyListValided = data.filter(data.dated => data.dated.filter(d => d.) !== 0);
          this.dataSource = new MatTableDataSource<Sondage>(this.surveyList);
          console.log(data);
          this.dataSource.paginator = this.paginator;
      });

      this.ds.getSurveyListCreatedOrderByUser(currentUser).subscribe((data) => {
           this.userSurveyTotal = data? data.length: 0;
           this.dataSource2 = new MatTableDataSource<Sondage>(data);
           this.dataSource2.paginator = this.paginator2;
           this.ds.countSurveyValided(currentUser).subscribe((c) => {
             this.surveyValided = c ? c : 0 ;
             this.surveyNoValided =  this.userSurveyTotal - c ;
         });
      });
      // console.log(this.invitationValided);
    }

    getTotal(data){
      this.invitationValided += data;
      this.invitationNoValided =  this.surveyListTotal -  this.invitationValided;
      console.log(this.invitationValided);
    }

    getResponse(response){
      // console.log(meetingIscreateg);
      this.meetingIscreated = response;
    }


}
