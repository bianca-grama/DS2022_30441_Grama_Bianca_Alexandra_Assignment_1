import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Person} from "../classes/person";

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  //name: String;
  private getAllUrl = "http://localhost:8080/person"
  private saveUrl = "http://localhost:8080/person"
  private loginUrl = "http://localhost:8080/person/login"
  private deleteUrl = "http://localhost:8080/person"
  private updateUrl = "http://localhost:8080/person"

  constructor(private httpClient: HttpClient) {
  }

  getPersonList(): Observable<Person[]> {
    return this.httpClient.get<Person[]>(`${this.getAllUrl}`);
  }

  createPerson(person: Person): Observable<Object> {
    return this.httpClient.post(`${this.saveUrl}`, person);
  }

  login(person: Person): Observable<Object> {
    return this.httpClient.post(`${this.loginUrl}`, person);
  }

  delete(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.deleteUrl}/${id}`)
  }

  update(id: number, person:Person): Observable<Object> {
    return this.httpClient.put(`${this.updateUrl}/${id}`, person);
  }

  logout() {
    localStorage.removeItem('currentUser');
  }

}
