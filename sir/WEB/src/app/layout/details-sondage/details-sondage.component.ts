// import { DetailsSondageService } from './../../../../.history/src/app/service/details-sondage.service_20200505030741';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params, ParamMap } from '@angular/router';
import { DetailsSondageService } from 'src/app/service/details-sondage.service';

@Component({
  selector: 'app-details-sondage',
  templateUrl: './details-sondage.component.html',
  styleUrls: ['./details-sondage.component.css']
})
export class DetailsSondageComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
   // private location: Location,
    private ds: DetailsSondageService
  ) { }

  ngOnInit() {
    // this.composer = this.route.paramMap
    // .switchMap((params: ParamMap) =>
    //   this.composerService.getComposerWithKey(params.get('id')));
  }

}
