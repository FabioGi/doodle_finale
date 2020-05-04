export class Creneau {
  public date: Date;
  public heure_debut: string;
  public heure_fin: string;
  public pause: boolean;
  public valided = false;

  constructor(src: Creneau) {
      this.date = src.date;
      this.heure_debut = src.heure_debut;
      this.heure_fin = src.heure_fin;
      this.pause = src.pause;
      this.valided = src.valided;
  }

}
