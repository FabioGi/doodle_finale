// import { DetailsSondageService } from './../../../../.history/src/app/service/details-sondage.service_20200505030741';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params, ParamMap } from '@angular/router';
import { DetailsSondageService } from 'src/app/service/details-sondage.service';
import { switchMap, startWith, tap, filter, retry, take } from 'rxjs/operators';
import { Reunion } from './reunion';

@Component({
  selector: 'app-details-sondage',
  templateUrl: './details-sondage.component.html',
  styleUrls: ['./details-sondage.component.css']
})
export class DetailsSondageComponent implements OnInit {

  reunion: Reunion;
  invite = ['Fabrice Kadio', 'yves kouassi','Orthnel konan'];
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ds: DetailsSondageService
  ) { }

  ngOnInit() {
    this.route.paramMap.pipe(switchMap((params: ParamMap) =>
         this.ds.getSondageDetails(params.get('id')))).subscribe((data:Reunion) => {
           this.reunion = {id: data.id,titre:data.titre,lieu:data.lieu,resume:data.resume,dated:data.dated, user: data.user };
           console.log(data);
         }) ;
  }

}
