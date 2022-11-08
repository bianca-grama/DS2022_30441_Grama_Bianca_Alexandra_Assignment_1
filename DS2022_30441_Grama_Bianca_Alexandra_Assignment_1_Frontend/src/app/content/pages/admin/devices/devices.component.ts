import {Component, OnInit} from "@angular/core";
import {Device} from "../../../../classes/device";
import {DeviceService} from "../../../../services/device.service";

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.css']
})

export class DevicesComponent implements OnInit {

  devices: Device[] = [];
  deviceToBeEdited: Device = new Device();
  editedDevice: Device = new Device();
  createdDevice: Device = new Device();
  adminLoggedIn: boolean = false;
  editing: boolean = false;
  creating: boolean = false;

  constructor(private deviceService: DeviceService) {
  }

  ngOnInit(): void {
    this.checkAdmin();
    this.getDeviceList();
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

  edit(device: Device){
    this.editing = true;
    this.deviceToBeEdited = device;
  }

  submitEdit() {

    this.editedDevice.id = this.deviceToBeEdited.id;
    if (this.editedDevice.description == '' || this.editedDevice.description == null){
      this.editedDevice.description = this.deviceToBeEdited.description;
    }
    if (this.editedDevice.address == '' || this.editedDevice.address == null){
      this.editedDevice.address = this.deviceToBeEdited.address;
    }
    if (this.editedDevice.maxConsumption == null){
      this.editedDevice.maxConsumption = this.deviceToBeEdited.maxConsumption;
    }
    if (this.editedDevice.ownerId == null){
      this.editedDevice.ownerId = this.deviceToBeEdited.ownerId;
    }
    console.log(this.editedDevice);
    this.deviceService.update(this.editedDevice.id, this.editedDevice).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));

    this.editing = false;
  }

  delete(device: Device){
    this.deviceService.delete(device.id).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
  }

  createNewDevice(){
    this.creating = true;
  }

  submitCreate() {

    this.deviceService.createDevice(this.createdDevice).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));

    this.creating = false;
  }

}
