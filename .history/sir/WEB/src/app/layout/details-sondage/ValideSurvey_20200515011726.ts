export class ValideSurvey {
  id: number;
  emails: string[];
  subject: string;
  content: string;
  constructor(id: number, emails: string[],subject: string, content: string) {
    this.id = id;
    this.emails = emails;
    this.subject = subject;
    this.content = content;
  }
}
