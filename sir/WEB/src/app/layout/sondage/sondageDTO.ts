import { Sondage } from './sondage';
import { Creneau } from './creneau';

export class SondageDTO {
  public sondage: Sondage;
  public creneau: Creneau[];
  public mails: string[];
  constructor(src:SondageDTO){
      this.creneau = src.creneau;
      this.sondage = src.sondage;
      this.mails = src.mails;
  }
}
