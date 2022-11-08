import {Component} from "@angular/core";
import {Person} from "../../../classes/person";
import {PersonService} from "../../../services/person.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent {

  person: Person = new Person();

  constructor(private personService: PersonService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  savePerson() {
    this.person.id = 0;
    this.person.role = 0; //regular user role for every new account
    this.personService.createPerson(this.person).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
    this.router.navigate(['login']).then(r => console.log(r));
  }

  onSubmit() {
    this.savePerson();
  }
}
