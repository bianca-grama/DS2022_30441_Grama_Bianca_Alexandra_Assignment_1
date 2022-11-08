import {Component, OnInit} from "@angular/core";
import {Device} from "../../../../classes/device";
import {DeviceService} from "../../../../services/device.service";
import {Person} from "../../../../classes/person";

@Component({
  selector: 'app-my-devices',
  templateUrl: './my-devices.component.html',
  styleUrls: ['./my-devices.component.css']
})

export class MyDevicesComponent implements OnInit {

  devices: Device[] = [];

  constructor(private deviceService: DeviceService) {
  }

  ngOnInit(): void {
    this.getDeviceList();
  }

  private getDeviceList() {
    let pers: Person = new Person();
    // @ts-ignore
    pers = JSON.parse(localStorage.getItem('currentUser'));
    this.deviceService.getDevicesByOwner(pers.id).subscribe(data => {
      this.devices = data;
      console.log(data);
    })
  }

}
