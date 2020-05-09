// import { DetailsSondageService } from './../../../../.history/src/app/service/details-sondage.service_20200505030741';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params, ParamMap, NavigationEnd } from '@angular/router';
import { DetailsSondageService } from 'src/app/service/details-sondage.service';
import { switchMap, startWith, tap, filter, retry, take } from 'rxjs/operators';
import { Reunion } from './reunion';
import { Vote } from './Vote';

@Component({
  selector: 'app-details-sondage',
  templateUrl: './details-sondage.component.html',
  styleUrls: ['./details-sondage.component.css']
})
export class DetailsSondageComponent implements OnInit {

  reunion: Reunion;
  invite = ['Fabrice Kadio', 'yves kouassi','Orthnel konan'];
  invitations: any;
  userMail: string;
  creneau = [];
  counter: any;
  idSurvey: string;
  disable: boolean = false;
  result: any;
  response: any;
  mySubscription: any;
  public pieChartLabels: Date[] = [] ;
  public pieChartData: number[] = [1,1,1,1,1,1,1,1,1];
  public pieChartType: string;
  totale = [];
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ds: DetailsSondageService
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };

    this.mySubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.router.navigated = false;
      }
    });
  }

  ngOnInit() {
    this.userMail = sessionStorage.getItem('email');
    this.route.paramMap.pipe(switchMap((params: ParamMap) =>
         this.ds.getSondageDetails(params.get('id')))).subscribe((data:Reunion) => {
           this.reunion = {id: data.id,titre:data.titre,lieu:data.lieu,resume:data.resume,dated:data.dated, user: data.user };
           console.log(data.dated);
           data.dated.forEach((data)=>{
            this.pieChartLabels.push(data.date  );
           })
           console.log( this.pieChartLabels);
           // this.pieChartLabels = data.dated.
         }) ;

    this.route.paramMap.pipe(switchMap((params: ParamMap) =>
         this.ds.getInvitationListOrderBySondage(params.get('id')))).subscribe((data) => {
           this.invitations = data;
           console.log(data);
         }) ;

    this.route.params.subscribe((params: Params) => {
      this.idSurvey = params['id'];
  });

    this.pieChartType = 'pie';
  }

  getTotal(total){
    // console.log(total);
    this.totale.push(total);
    console.log(this.pieChartData);
    this.pieChartData = this.totale;
  }
  ngOnDestroy() {
    if (this.mySubscription) {
      this.mySubscription.unsubscribe();
    }
  }

 //
  selectedDate(date,email) {
    console.log(this.response);
    if (this.userMail === email) {
      if (!this.response) {
        this.creneau.push(date.id);
        // this.isCheked(date.id);

      } else {
        const index = this.creneau.indexOf(date.id);
        if (index > - 1) { this.creneau.splice(index, 1);  }
      }
      console.log( this.creneau);
    }
  }
  onChange(val){
    this.response = val;
  }

  validerVote() {
    if (this.creneau.length !== 0) {
      const vote = new Vote(this.userMail, this.creneau);
      console.log(vote);
      this.ds.choseDateToMeeting(vote).subscribe(
        (data) => {
           console.log(data);
       },
       error => {
        console.log(error.message);
      });
    }
   // this.disable = true;
    this.router.navigate(['/sondage-details/' + this.idSurvey]);
  }


  getCounterSlot(slot, survey) {
      this.ds.countSlotOrderByUser(slot, survey).subscribe((count) => {
            this.counter = count;
      });
  }

}
