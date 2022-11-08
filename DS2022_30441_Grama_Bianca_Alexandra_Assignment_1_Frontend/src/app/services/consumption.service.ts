import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Consumption} from "../classes/consumption";

@Injectable({
  providedIn: 'root'
})
export class ConsumptionService {

  private getByDeviceUrl = "http://localhost:8080/consumption"

  constructor(private httpClient: HttpClient) {
  }


  getConsumptionByDevice(id: number): Observable<Consumption[]> {
    return this.httpClient.get<Consumption[]>(`${this.getByDeviceUrl}/${id}`);
  }

}
