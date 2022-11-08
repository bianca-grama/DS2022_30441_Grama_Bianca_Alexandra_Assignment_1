import {Component, OnInit} from "@angular/core";
import {ChartModel} from "src/app/classes/chart-model";
import {Device} from "../../../../classes/device";
import {Person} from "../../../../classes/person";
import {DeviceService} from "../../../../services/device.service";
import {ConsumptionService} from "../../../../services/consumption.service";
import {Consumption} from "../../../../classes/consumption";

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})

export class ChartsComponent implements OnInit {

  chartList: Array<Array<ChartModel>> = [];
  devices: Device[];
  date: Date;

  maxHeight = 200;

  constructor(private deviceService: DeviceService,
              private consumptionService: ConsumptionService) {
  }

  ngOnInit(): void {
    this.getDevices();
  }

  private getDevices() {
    let pers: Person = new Person();
    // @ts-ignore
    pers = JSON.parse(localStorage.getItem('currentUser'));
    this.deviceService.getDevicesByOwner(pers.id).subscribe(data => {
      this.devices = data;
      console.log(data);
    })
  }

  getValues() {
    console.log('selected date');
    console.log(this.date);
    console.log(this.date.toString());
    console.log(this.date.toDateString());

    this.devices.forEach(device => {
      this.consumptionService.getConsumptionByDevice(device.id).subscribe(data =>{
        let consumptions : Consumption[] = [];
        consumptions = data;
        let chartModelsForThisDevice: ChartModel[] = [];
        consumptions.forEach(consumption => {
          if (consumption.timestamp.startsWith(this.date.toDateString())){
            let chartModel : ChartModel = new ChartModel();
            chartModel.value = consumption.consumption;
            chartModel.color = '#498B94';
            chartModel.legend = consumption.timestamp;
            chartModel.size = Math.round((chartModel.value * this.maxHeight) / 1000) + '%';
            chartModelsForThisDevice.push(chartModel);
          }
        });
        this.chartList[device.id] = chartModelsForThisDevice;
      });
    });

  }


}
