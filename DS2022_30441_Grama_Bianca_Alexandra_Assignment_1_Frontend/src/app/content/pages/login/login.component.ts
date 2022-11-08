import {Component} from "@angular/core";
import {Person} from "../../../classes/person";
import {PersonService} from "../../../services/person.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {

  person: Person = new Person();
  loginError: boolean = false;
  loginSuccess: boolean = false;

  loginFailAlert: string = "Username and password did not match, or username does not exist. Please try again.";
  loginSuccessAlert: string = "Logged in successfully";

  constructor(private personService: PersonService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.logout();
    console.log(localStorage);
  }

  login() {
    this.personService.login(this.person).subscribe(data => {
        localStorage.setItem('currentUser', JSON.stringify(data));
        let pers: Person = new Person();
        // @ts-ignore
        pers = data;
        if(pers.role == 0){ //client
          this.router.navigate(['my-devices']).then(r => console.log(r));
          this.loginSuccess = true;
          this.loginError = false;
          return;
        } else if (pers.role == 1){ //admin
          this.router.navigate(['persons']).then(r => console.log(r));
          this.loginSuccess = true;
          this.loginError = false;
          return;
        }
      },
      error => {
        console.log(error);
        this.loginSuccess = false;
        this.loginError = true;
      });

  }

  logout() {
    this.personService.logout();
    this.router.navigate(['login']).then(r => console.log(r));
  }

  onSubmit() {
    this.login();
  }

}
