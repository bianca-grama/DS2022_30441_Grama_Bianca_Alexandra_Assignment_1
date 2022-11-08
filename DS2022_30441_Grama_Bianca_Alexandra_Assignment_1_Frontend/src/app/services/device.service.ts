import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Device} from "../classes/device";

@Injectable({
  providedIn: 'root'
})
export class DeviceService {

  //name: String;
  private getAllUrl = "http://localhost:8080/device"
  private getByOwnerUrl = "http://localhost:8080/device/owned-by"
  private saveUrl = "http://localhost:8080/device"
  private deleteUrl = "http://localhost:8080/device"
  private updateUrl = "http://localhost:8080/device"

  constructor(private httpClient: HttpClient) {
  }

  getDeviceList(): Observable<Device[]> {
    return this.httpClient.get<Device[]>(`${this.getAllUrl}`);
  }

  createDevice(device: Device): Observable<Object> {
    return this.httpClient.post(`${this.saveUrl}`, device);
  }

  delete(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.deleteUrl}/${id}`)
  }

  update(id: number, device:Device): Observable<Object> {
    return this.httpClient.put(`${this.updateUrl}/${id}`, device);
  }

  getDevicesByOwner(id:number): Observable<Device[]> {
    return this.httpClient.get<Device[]>(`${this.getByOwnerUrl}/${id}`);
  }

}
