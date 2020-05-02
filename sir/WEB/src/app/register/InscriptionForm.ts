import { User } from '../service/authentication.service';
import { Allergie } from './Allergie';
import { Preference } from './Preferences';

export class InscriptionForm {
  public user: User;
  public allergies: Allergie[];
  public preferences: Preference[];

  constructor(user:User,allergies:Allergie[], preferences:Preference[]) {
       this.user = user;
       this.allergies = allergies;
       this.preferences = preferences;
  }

}


