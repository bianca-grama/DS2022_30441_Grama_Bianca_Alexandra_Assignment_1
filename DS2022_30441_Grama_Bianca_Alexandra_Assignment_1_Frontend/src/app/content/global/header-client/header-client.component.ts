import {Component} from "@angular/core";
import {MenuItem} from 'primeng/api';
import {Router} from "@angular/router";

@Component({
  selector: 'app-header-client',
  templateUrl: './header-client.component.html',
  styleUrls: ['./header-client.component.css']
})

export class HeaderClientComponent {

  items: MenuItem[] = [];
  loggedIn: boolean = false;

  constructor(private router: Router) {
  }

  ngOnInit() {
    if (localStorage.getItem('currentUser')) {
      this.loggedIn = true;
    } else {
      this.loggedIn = false;
    }

    this.items = [
      {
        label: 'My devices',
        icon: 'pi pi-fw pi-calculator',
        routerLink: ['/my-devices']
      },
      {
        label: 'Charts',
        icon: 'pi pi-fw pi-chart-bar',
        routerLink: ['/charts']
      }
    ];
  }

  logout() {
    localStorage.removeItem('currentUser');
    console.log(localStorage);
    this.router.navigate(['login']).then(r => console.log(r));
  }
}
