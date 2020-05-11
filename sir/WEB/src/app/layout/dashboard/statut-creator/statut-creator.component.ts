import { Component, OnInit, Input } from "@angular/core";
import { DetailsSondageService } from 'src/app/service/details-sondage.service';

@Component({
  selector: "app-statut-creator",
  templateUrl: "./statut-creator.component.html",
  styleUrls: ["./statut-creator.component.scss"]
})

export class StatutCreatorComponent implements OnInit {
  @Input()
  id: number;
  statut = [];
  constructor(private ds: DetailsSondageService) {

  }

  ngOnInit() {
      this.ds.getSondageDetails(this.id).subscribe((data)=>{
        this.statut = data.dated.filter( survey => survey.valided === true);
        console.log(this.statut);
      });
  }
}
