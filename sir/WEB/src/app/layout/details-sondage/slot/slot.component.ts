import { Observable } from 'rxjs';
import { Component, OnInit, Input } from '@angular/core';
import { DetailsSondageService } from 'src/app/service/details-sondage.service';

@Component({
  selector: "app-slot",
  templateUrl: "./slot.component.html",
  styleUrls: ["./slot.component.scss"]
})

export class SlotComponent implements OnInit {
@Input()
slotId: number;
@Input()
sondageId: number ;
  counter: Observable<number[]>;



  constructor(private ds : DetailsSondageService) {
  }


  ngOnInit() {
    this.counter = this.ds.countSlotOrderByUser(this.slotId, this.sondageId);
  }
}
