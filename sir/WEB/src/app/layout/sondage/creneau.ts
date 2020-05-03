export class Creneau {
  public dateReunion: Date;
  public heureDebut: string;
  public heureFin: string;

  constructor(src:Creneau) {
      this.dateReunion = src.dateReunion;
      this.heureDebut = src.heureDebut;
      this.heureFin = src.heureFin;
  }

}
