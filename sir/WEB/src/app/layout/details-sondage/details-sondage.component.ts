// import { DetailsSondageService } from './../../../../.history/src/app/service/details-sondage.service_20200505030741';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params, ParamMap } from '@angular/router';
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
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ds: DetailsSondageService
  ) { }

  ngOnInit() {
    this.userMail = sessionStorage.getItem('email');
    this.route.paramMap.pipe(switchMap((params: ParamMap) =>
         this.ds.getSondageDetails(params.get('id')))).subscribe((data:Reunion) => {
           this.reunion = {id: data.id,titre:data.titre,lieu:data.lieu,resume:data.resume,dated:data.dated, user: data.user };
           console.log(data);
         }) ;

    this.route.paramMap.pipe(switchMap((params: ParamMap) =>
         this.ds.getInvitationListOrderBySondage(params.get('id')))).subscribe((data) => {
           this.invitations = data;
           console.log(data);
         }) ;

  }

  selectedDate(date) {
    if (!date.valided) {
      this.creneau.push(date.id);
    } else {
      const index = this.creneau.indexOf(date.id);
      if (index > - 1) { this.creneau.splice(index, 1);  }
    }
  }

  validerVote() {
    if (this.creneau.length !== 0) {
      const vote = new Vote(this.userMail, this.creneau);
      console.log(vote);
      this.ds.choseDateToMeeting(vote).subscribe(
        (data)=>{
           console.log(data);
       },
       error =>
      {
        console.log(error.message);
      });
    }
  }

}
