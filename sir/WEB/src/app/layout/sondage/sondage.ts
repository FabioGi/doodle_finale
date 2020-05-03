export class Sondage {
  public lieu: string;
  public titre: string;
  public resume: string;
  public userMails: string;
  // constructor(lieu: string, titre: string, resume: string, userMails: string) {
  //     this.lieu      = lieu;
  //     this.resume    = resume;
  //     this.titre     = titre;
  //     this.userMails = userMails;
  // }

  updateSondage(lieu: string, titre: string, resume: string, userMails: string) {
    this.lieu      = lieu;
    this.resume    = resume;
    this.titre     = titre;
    this.userMails = userMails;

  }
}
