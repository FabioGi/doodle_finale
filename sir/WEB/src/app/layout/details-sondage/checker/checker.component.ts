import { Creneau } from './../../sondage/creneau';
import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
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
  // @Input()
  // date: Creneau;
  @Output()
  results = new EventEmitter();
  choice: number[];
  result: boolean;
  creneau = [];
  userMail: string;
  disable: boolean;
  constructor(private ds: DetailsSondageService) {
    this.userMail = sessionStorage.getItem('email');
  }

  ngOnInit() {
            this.ds.getUserChoice(this.slotID,this.email).subscribe((data)=>{
              this.choice = data;
              this.disable = this.choice.length != 0 ;
              console.log(data);
              this.isCheked(this.id_cr);
          },
          error => {
                console.log(error.message);
          });


  }

  isCheked(id) {
    this.result = this.choice.includes(id);
  }
  ischecked(val){
    this.results.emit(val);
    console.log(val);
  }

  // selectedDate(date) {
  //   if (!date.valided) {
  //     this.creneau.push(date.id);

  //   } else {
  //     const index = this.creneau.indexOf(date.id);
  //     if (index > - 1) { this.creneau.splice(index, 1);  }
  //     // console.log(this.creneau);
  //   }
  //   this.results.emit(this.creneau);
  //   console.log(this.creneau);
  // }


}
