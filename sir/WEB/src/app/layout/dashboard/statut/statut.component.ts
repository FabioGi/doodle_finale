import { Component, OnInit, Input } from "@angular/core";
import { DetailsSondageService } from 'src/app/service/details-sondage.service';

@Component({
  selector: "app-statut",
  templateUrl: "./statut.component.html",
  styleUrls: ["./statut.component.scss"]
})

export class StatutComponent implements OnInit {
  @Input()
  slotID: number ;
  @Input()
  email: string;
  response: boolean;
  constructor(private ds: DetailsSondageService) {

  }

  ngOnInit() {
      this.ds.getUserChoice(this.slotID,this.email).subscribe((data)=>{
        this.response = data.length !== 0 ;
        console.log(data);
    },
    error => {
          console.log(error.message);
    });
  }
}
