import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipInputEvent} from '@angular/material/chips';

import { Component, OnInit, Input } from "@angular/core";
import { DetailsSondageService } from 'src/app/service/details-sondage.service';

@Component({
  selector: "app-alimentaire",
  templateUrl: "./alimentaire.component.html",
  styleUrls: ["./alimentaire.component.scss"]
})

export class AlimentaireComponent implements OnInit {

  @Input()
  email: string ;
  Preference: any;
  allergies: any;
  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  constructor(private ds: DetailsSondageService ) {

  }

  ngOnInit() {
      this.ds.getPreferences(this.email).subscribe((data)=>{
        this.Preference = data ;
      });

      this.ds.getAllergie(this.email).subscribe((data) => {
        this.allergies = data ;
      });
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || '').trim()) {
      this.fruits.push({name: value.trim()});
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(fruit: Fruit): void {
    const index = this.fruits.indexOf(fruit);

    if (index >= 0) {
      this.fruits.splice(index, 1);
    }
  }

}
