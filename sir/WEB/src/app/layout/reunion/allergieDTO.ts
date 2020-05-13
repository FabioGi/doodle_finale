export class AllergieDTO {
  name: string;
  email: string;
  update(name,email){
    this.name = name;
    this.email = email;
  }
}
