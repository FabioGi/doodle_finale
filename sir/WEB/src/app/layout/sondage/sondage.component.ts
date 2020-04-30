import { Component, OnInit, ViewChild } from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';


@Component({
  selector: 'app-sondage',
  templateUrl: './sondage.component.html',
  styleUrls: ['./sondage.component.scss']
})
export class SondageComponent implements OnInit {
  isLinear = false;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
 //  public dateControl = new FormControl(new Date(2021,9,4,5,6,7));
  dateControl1 = new FormControl(null, [Validators.required]);
  dateControl2 = new FormControl(null, [Validators.required]);
  dateControl3 = new FormControl(null, [Validators.required]);
  public dateControlMinMax = new FormControl(new Date());
  events = [ ];
  displayedColumns: string[] = ['Date', 'Heure debut', 'Heure fin'];
  checked = false;
  // @ViewChild('picker',) picker: any;



  constructor(private _formBuilder: FormBuilder) { }

  ngOnInit() {
      this.firstFormGroup = this._formBuilder.group({
        firstCtrl: ['', Validators.required]
      });
      this.secondFormGroup = this._formBuilder.group({
        secondCtrl: ['', Validators.required]
      });
    }

    // events = [
    //   {
    //     start: new FormControl(new Date(2021,9,4,5,6,7)),
    //     end: new FormControl(new Date(2021,9,4,5,6,7))
    //   },
    //   {
    //     start: new FormControl(new Date(2021,9,4,5,6,7)),
    //     end: new FormControl(new Date(2021,9,4,5,6,7))
    //   },
    //   {
    //     start: new FormControl(new Date(2021,9,4,5,6,7)),
    //     end: new FormControl(new Date(2021,9,4,5,6,7))
    //   },
    // ];

    // public formGroup = new FormGroup({
    //   date: new FormControl(null, [Validators.required]),
    //   date2: new FormControl(null, [Validators.required])
    // })
    addEvent() {
      // console.log(this.dateControl1.value);
      // console.log(this.dateControl2);
            this.events.push({
            // start: (new Date(this.dateControl1.value)).toString().replace(/\S+\s(\S+)\s(\d+)\s(\d+)\s.*/,'$2-$1-$3'),
            // end:   (new Date(this.dateControl2.value)).toString().replace(/\S+\s(\S+)\s(\d+)\s(\d+)\s.*/,'$2-$1-$3')
             date: new Date(this.dateControl1.value),
            start: new Date(this.dateControl2.value),
              end: new Date(this.dateControl3.value)
            });


      // this.dateControl1 = new FormControl(null, [Validators.required]);
      // this.dateControl2 = new FormControl(null, [Validators.required]);
      // this.dateControl3 = new FormControl(null, [Validators.required]);
      // console.log(this.events[0].start.getDate());
      // console.log(this.events[0].start.getHours());
      // console.log(this.events[0].start.getMinutes());

    }

}
