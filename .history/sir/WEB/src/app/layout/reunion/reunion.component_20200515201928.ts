
import { Component, OnInit, Inject } from "@angular/core";
import { ActivatedRoute, Router, ParamMap, Params } from '@angular/router';
import { DetailsSondageService } from 'src/app/service/details-sondage.service';
import { switchMap } from 'rxjs/operators';
import { Creneau } from '../sondage/creneau';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ReunionService } from 'src/app/service/reunion.service';
import { AllergieDTO } from './allergieDTO';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: "app-reunion",
  templateUrl: "./reunion.component.html",
  styleUrls: ["./reunion.component.scss"]
})

export class ReunionComponent implements OnInit {
  userMail: string;
  dateSelected: any;
  attendlist: any;
  idMeeting: any;
  meeting: any;
  creneau: Creneau;
  animal: any;
  mail: any;
  ngxQrcode2 = null;

  title = 'code d entrer';
  elementType = 'url';
  value = 'Techiediaries';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ds: DetailsSondageService,
    public dialog: MatDialog
  ) {

  }

  ngOnInit() {
    this.userMail = sessionStorage.getItem('email');
    this.route.paramMap.pipe(switchMap((params: ParamMap) =>
    this.ds.getSondageDetails(params.get('id')))).subscribe((data) => {
      this.meeting = data ;
      this.dateSelected = data.dated.filter( survey => survey.valided === true);
      this.creneau = this.dateSelected[0];
      console.log(this.creneau);
      if (this.dateSelected[0]) {
       this.ds.getAttendlist(this.dateSelected[0].id, this.idMeeting).subscribe((attendlist) => {
         this.attendlist = attendlist ;
         });
      }
    }) ;

    this.route.params.subscribe((params: Params) => {
      this.idMeeting = params['id'];
      this.ngxQrcode2 =  'batiment' + this.idMeeting;
    });
  }

  openDialog(id, email): void {
    const dialogRef = this.dialog.open(AlimentaireDialog, {
      width: '800px',
      height: '350x',
      data: id
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.mail = id;
    });
  }

}




@Component({
  selector: 'app-alimentaire-dialog',
  templateUrl: 'alimentaire-dialog.html',
})
export class AlimentaireDialog {
  allergies: any;
  preferences: any;
  pvalue = '';
  value = '';
  currentuser: string;

  constructor(
    public dialogRef: MatDialogRef<AlimentaireDialog>,
    @Inject(MAT_DIALOG_DATA) public data: string,
    private rs: ReunionService) {
      this.currentuser = sessionStorage.getItem('email');
      this.rs.getAllergie(data).subscribe((allergie) => {
        this.allergies = allergie;
        console.log(this.allergies);
      });

      this.rs.getPreferences(data).subscribe((preference) => {
        this.preferences = preference;
        console.log(this.preferences);
      });
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

  deletePreference(id) {
     this.rs.deletePreferences(id).subscribe(() => {
       console.log('preference supprime avec succes');
       this.onNoClick();
     },
     error => {
       console.log(error.message);
     });
  }

  deleteAllergie(id){
    this.rs.deleteAllergie(id).subscribe(() => {
      console.log('allergie supprime avec succes');
      this.onNoClick();
    },
    error => {
      console.log(error.message);
    });
  }

  addPreference() {

    const preference = new AllergieDTO();
    preference.update(this.pvalue, this.data);
    this.rs.addPreference(preference).subscribe(() => {
      console.log('donnee inscrit avec succes');
      this.onNoClick();
    });
  }

    addAllergie() {
      const allergie = new AllergieDTO();
      allergie.update(this.value, this.data);
      this.rs.addAllergie(allergie).subscribe(() => {
        console.log('donnee inscrit avec succes');
        this.onNoClick();
      });
  }
}



