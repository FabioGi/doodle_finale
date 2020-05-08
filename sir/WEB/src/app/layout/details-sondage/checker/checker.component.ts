import { Component, OnInit, Input } from "@angular/core";
import { DetailsSondageService } from 'src/app/service/details-sondage.service';

@Component({
  selector: "app-checker",
  templateUrl: "./checker.component.html",
  styleUrls: ["./checker.component.scss"]
})

export class CheckerComponent implements OnInit {

  @Input()
  slotID: number ;
  @Input()
  email: string;
  @Input()
  id_cr: string;
  choice: number[];
  result: boolean;
  constructor(private ds: DetailsSondageService) {
  }

  ngOnInit() {
            this.ds.getUserChoice(this.slotID,this.email).subscribe((data)=>{
              this.choice = data;
              console.log(data);
              this.isCheked(this.id_cr);
          },
          error => {
                console.log(error.message);
          });


  }

  isCheked(id) {
    this.result = this.choice.includes(id);
    console.log(this.result);
  }
}
