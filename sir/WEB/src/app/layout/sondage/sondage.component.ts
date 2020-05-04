import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatAutocompleteSelectedEvent, MatAutocomplete} from '@angular/material/autocomplete';
import {MatChipInputEvent} from '@angular/material/chips';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { Creneau } from './creneau';
import { Sondage } from './sondage';
import { SondageService } from 'src/app/service/sondage.service';
export interface SelctedDate {
     date: Date;
     start: Date;
     end: Date;
}

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
  mailsCtrl = new FormControl();
  filteredFruits: Observable<string[]>;
  fruits: string[] = ['Lemon'];
  allFruits: string[] = ['Apple', 'Lemon', 'Lime', 'Orange', 'Strawberry'];

  @ViewChild('fruitInput',{static: false}) fruitInput: ElementRef<HTMLInputElement>;
  @ViewChild('auto',{static: false}) matAutocomplete: MatAutocomplete;
  mails = [];
  allmails = [];
  filteredMails: Observable<any[]>;
  allEmail: Observable<any>;
  // @ViewChild('picker',) picker: any;



  constructor(private _formBuilder: FormBuilder,
              private ss: SondageService) {
    // this.filteredFruits = this.fruitCtrl.valueChanges.pipe(
    //   startWith(null),
    //   map((fruit: string | null) => fruit ? this._filter(fruit) : this.allFruits.slice()));

    this.ss.getUsersMail().subscribe((data) => {
     // this.allmails =  data;
      this.filteredMails =  this.mailsCtrl.valueChanges.pipe(
        startWith(null),
        map((mail: string | null) => {
           return mail ? this._filter(mail, data) : data.slice();
        })
      );
    });


    // this.filteredMails =  this.mailsCtrl.valueChanges.pipe(
    //     startWith(null),
    //     map((user) => {
    //       return user ? this._filter(user) : this.mails.slice();
    //     })
    //   );
  }

  ngOnInit() {
    const userMail = sessionStorage.getItem('email');
    this.firstFormGroup = this._formBuilder.group({
        titreCtrl: ['', Validators.required],
        lieuCtrl: ['', Validators.required],
        resumeCtrl: ['', Validators.required],
        mailCtrl: [userMail]
      });
    this.secondFormGroup = this._formBuilder.group({
        secondCtrl: ['', Validators.required]
      });
    }

    add(event: MatChipInputEvent): void {
      const input = event.input;
      const value = event.value;

      // Add our fruit
      if ((value || '').trim()) {
        this.mails.push(value.trim());
      }

      // Reset the input value
      if (input) {
        input.value = '';
      }

      this.mailsCtrl.setValue(null);
    }

    remove(fruit: string): void {
      const index = this.mails.indexOf(fruit);

      if (index >= 0) {
        this.mails.splice(index, 1);
      }
    }

    selected(event: MatAutocompleteSelectedEvent): void {
      this.mails.push(event.option.viewValue);
     // console.log(event.option.viewValue);
      this.fruitInput.nativeElement.value = '';
      this.mailsCtrl.setValue(null);
    }

    private _filter(value, data) {
      console.log(value);
      const filterValue = value.toLowerCase();
      console.log(filterValue);

      return data.filter(mail => mail.email.toLowerCase().indexOf(filterValue) === 0);
    }

    addEvent() {
            this.events.push({
             date : new Date(this.dateControl1.value),
             start: new Date(this.dateControl2.value),
             end  : new Date(this.dateControl3.value)
            });

    }

    creerSondage() {
      const creneaux: Creneau[] = [];
      const sondage = new Sondage();
      const lieu: string   = this.firstFormGroup.value.lieuCtrl   ? this.firstFormGroup.value.lieuCtrl   : null;
      const mail: string   = this.firstFormGroup.value.mailCtrl   ? this.firstFormGroup.value.mailCtrl   : null;
      const resume: string = this.firstFormGroup.value.resumeCtrl ? this.firstFormGroup.value.resumeCtrl : null;
      const titre: string  = this.firstFormGroup.value.titreCtrl  ? this.firstFormGroup.value.titreCtrl  : null;

      sondage.updateSondage(lieu, titre, resume, mail);
      this.events.forEach((creneau: SelctedDate) => {
        creneaux.push({dateReunion: creneau.date,
                      heureDebut: creneau.start.getHours().toString() + ':' + creneau.start.getMinutes().toString(),
                      heureFin  : creneau.end.getHours().toString()   + ':' + creneau.end.getMinutes().toString()});
      });
      console.log(sondage);
      console.log(creneaux);
      // recuperer tous les emails de la bd
    }

}
