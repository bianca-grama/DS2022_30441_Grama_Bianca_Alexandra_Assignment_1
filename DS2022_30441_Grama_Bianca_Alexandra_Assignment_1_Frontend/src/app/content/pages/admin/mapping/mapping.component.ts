import {Component, OnInit} from "@angular/core";
import {Person} from "../../../../classes/person";
import {PersonService} from "../../../../services/person.service";
import {DeviceService} from "../../../../services/device.service";
import {Device} from "../../../../classes/device";
import {SelectItem} from "primeng/api";

@Component({
  selector: 'app-mapping',
  templateUrl: './mapping.component.html',
  styleUrls: ['./mapping.component.css']
})

export class MappingComponent implements OnInit {

  persons: Person[] = [];
  devices: Device[] = [];
  adminLoggedIn: boolean = false;
  selectedPerson: Person;
  selectedDevice: Device;

  constructor(private personService: PersonService,
              private deviceService: DeviceService) {
  }

  ngOnInit(): void {
    this.checkAdmin();
    this.getPersonList();
    this.getDeviceList();

  }

  private getPersonList() {
    this.personService.getPersonList().subscribe(data => {
      this.persons = data;
    })
  }

  private getDeviceList() {
    this.deviceService.getDeviceList().subscribe(data => {
      this.devices = data;
      console.log(data);
    })
  }



  checkAdmin() {
    if (localStorage.getItem('currentUser')) {
      // @ts-ignore
      let person = JSON.parse(localStorage.getItem('currentUser'));
      if (person.role == 1) {
        this.adminLoggedIn = true;
      } else {
        this.adminLoggedIn = false;
      }
    } else {
      this.adminLoggedIn = false;
    }
  }

  mapDevices(selectedPerson: Person, selectedDevice: Device) {
    console.log(selectedDevice);
    selectedDevice.ownerId = selectedPerson.id;
    console.log(selectedDevice);
    this.deviceService.update(this.selectedDevice.id, this.selectedDevice).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
  }

}
