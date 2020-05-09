export class ValideSurvey {
  id: number;
  emails: string[];
  constructor(id: number, emails: string[]) {
    this.id = id;
    this.emails = emails;
  }
}
