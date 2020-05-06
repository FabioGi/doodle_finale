export class Vote {
  email: string;
  choix: number[];
  constructor(email, choix) {
      this.email = email;
      this.choix = choix;
  }
}
