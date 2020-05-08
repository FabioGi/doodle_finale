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

counter: any;

  constructor(private ds : DetailsSondageService) {
  }


  ngOnInit() {
    this.ds.countSlotOrderByUser(this.slotId, this.sondageId).subscribe((count) => {
          this.counter = count;
    });
  }
}
