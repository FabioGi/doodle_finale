export class Sondage {
  public lieu: string;
  public titre: string;
  public resume: string;
  public userMails: string;
  public subject: string;
  public content: string;

  updateSondage(lieu: string, titre: string, resume: string, userMails: string,  subject: string, content: string) {
    this.lieu      = lieu;
    this.resume    = resume;
    this.titre     = titre;
    this.userMails = userMails;

  }
}
