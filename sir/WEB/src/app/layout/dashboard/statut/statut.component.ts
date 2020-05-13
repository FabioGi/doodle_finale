import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { DetailsSondageService } from 'src/app/service/details-sondage.service';

@Component({
  selector: "app-statut",
  templateUrl: "./statut.component.html",
  styleUrls: ["./statut.component.scss"]
})

export class StatutComponent implements OnInit {
  @Input()
  slotID: number ;
  email: string;
  response: boolean;
  counter = 0;
  @Output()
  total = new EventEmitter();
  @Output()
  isCreated = new EventEmitter();
  constructor(private ds: DetailsSondageService) {

  }

  ngOnInit() {
      this.email = sessionStorage.getItem('email');
      this.ds.getUserChoice(this.slotID,this.email).subscribe((data)=>{
        this.response = data.length !== 0 ;
        this.counter =  this.response ? 1 : 0;
        this.total.emit(this.counter);
        this.isCreated.emit(this.response);
        console.log(this.response);
        // console.log(this.email);
    },
    error => {
          console.log(error.message);
    });
  }
}



// import { Observable } from 'rxjs';
// import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
// import { DetailsSondageService } from 'src/app/service/details-sondage.service';

// @Component({
//   selector: "app-slot",
//   templateUrl: "./slot.component.html",
//   styleUrls: ["./slot.component.scss"]
// })

// export class SlotComponent implements OnInit {
// @Input()
// slotId: number;
// @Input()
// sondageId: number ;

// @Output()
// total = new EventEmitter();
//   counter: Observable<number[]>;



//   constructor(private ds : DetailsSondageService) {
//   }


//   ngOnInit() {
//     this.ds.countSlotOrderByUser(this.slotId, this.sondageId).subscribe((data)=>{
//       this.counter = data ;
//       this.total.emit( this.counter);
//     });

//   }
// }

