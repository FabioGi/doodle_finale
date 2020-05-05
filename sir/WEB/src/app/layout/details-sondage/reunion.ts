import { User } from 'src/app/register/User';
import { Creneau } from '../sondage/creneau';

export interface Reunion {
  id: number;
  titre: string;
  lieu: string;
  resume: string;
  user: User;
  dated: Creneau[];
}
