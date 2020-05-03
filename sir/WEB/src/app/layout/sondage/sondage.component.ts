import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatAutocompleteSelectedEvent, MatAutocomplete} from '@angular/material/autocomplete';
import {MatChipInputEvent} from '@angular/material/chips';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';



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
  visible = true;
  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  fruitCtrl = new FormControl();
  filteredFruits: Observable<string[]>;
  fruits: string[] = ['Lemon'];
  allFruits: string[] = ['Apple', 'Lemon', 'Lime', 'Orange', 'Strawberry'];
  @ViewChild('fruitInput',{static: false}) fruitInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto',{static: false}) matAutocomplete: MatAutocomplete;
  // @ViewChild('picker',) picker: any;



  constructor(private _formBuilder: FormBuilder) {
    this.filteredFruits = this.fruitCtrl.valueChanges.pipe(
      startWith(null),
      map((fruit: string | null) => fruit ? this._filter(fruit) : this.allFruits.slice()));
  }

  ngOnInit() {
      this.firstFormGroup = this._formBuilder.group({
        firstCtrl: ['', Validators.required]
      });
      this.secondFormGroup = this._formBuilder.group({
        secondCtrl: ['', Validators.required]
      });
      const userMail = sessionStorage.getItem("email");
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
    add(event: MatChipInputEvent): void {
      const input = event.input;
      const value = event.value;

      // Add our fruit
      if ((value || '').trim()) {
        this.fruits.push(value.trim());
      }

      // Reset the input value
      if (input) {
        input.value = '';
      }

      this.fruitCtrl.setValue(null);
    }

    remove(fruit: string): void {
      const index = this.fruits.indexOf(fruit);

      if (index >= 0) {
        this.fruits.splice(index, 1);
      }
    }

    selected(event: MatAutocompleteSelectedEvent): void {
      this.fruits.push(event.option.viewValue);
      this.fruitInput.nativeElement.value = '';
      this.fruitCtrl.setValue(null);
    }

    private _filter(value: string): string[] {
      const filterValue = value.toLowerCase();

      return this.allFruits.filter(fruit => fruit.toLowerCase().indexOf(filterValue) === 0);
    }

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
