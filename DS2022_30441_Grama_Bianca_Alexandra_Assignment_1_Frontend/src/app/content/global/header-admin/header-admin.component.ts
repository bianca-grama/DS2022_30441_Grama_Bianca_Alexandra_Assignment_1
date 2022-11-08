import {Component} from "@angular/core";
import {MenuItem} from 'primeng/api';
import {Router} from "@angular/router";

@Component({
  selector: 'app-header-admin',
  templateUrl: './header-admin.component.html',
  styleUrls: ['./header-admin.component.css']
})

export class HeaderAdminComponent {

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
        label: 'Users',
        icon: 'pi pi-fw pi-users',
        routerLink: ['/persons']

      },
      {
        label: 'Devices',
        icon: 'pi pi-fw pi-calculator',
        routerLink: ['/devices'] //TODO
      },
      {
        label: 'User-device mapping',
        icon: 'pi pi-fw pi-arrow-right-arrow-left',
        routerLink: ['/mapping'] //TODO
      }
    ];
  }

  logout() {
    localStorage.removeItem('currentUser');
    console.log(localStorage);
    this.router.navigate(['login']).then(r => console.log(r));

  }
}
