import {Component, OnInit} from "@angular/core";
import {Person} from "../../../../classes/person";
import {PersonService} from "../../../../services/person.service";

@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.css']
})

export class PersonsComponent implements OnInit {

  persons: Person[] = [];
  personToBeEdited: Person = new Person();
  editedPerson: Person = new Person();
  createdPerson: Person = new Person();
  adminLoggedIn: boolean = false;
  editing: boolean = false;
  creating: boolean = false;

  constructor(private personService: PersonService) {
  }

  ngOnInit(): void {
    this.checkAdmin();
    this.getPersonList();
  }

  private getPersonList() {
    this.personService.getPersonList().subscribe(data => {
      this.persons = data;
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

  edit(person: Person){
    this.editing = true;
    this.personToBeEdited = person;
  }

  submitEdit() {

    this.editedPerson.id = this.personToBeEdited.id;
    if (this.editedPerson.name == '' || this.editedPerson.name == null){
      this.editedPerson.name = this.personToBeEdited.name;
    }
    if (this.editedPerson.username == '' || this.editedPerson.username == null){
      this.editedPerson.username = this.personToBeEdited.username;
    }
    if (this.editedPerson.role == null){
      this.editedPerson.role = this.personToBeEdited.role;
    }

    this.editedPerson.password = this.personToBeEdited.password;

    console.log('persoana editata:', this.editedPerson)
    this.personService.update(this.editedPerson.id, this.editedPerson).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));

    this.editing = false;
  }

  delete(person: Person){
    this.personService.delete(person.id).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
  }

  createNewPerson(){
    this.creating = true;
  }

  submitCreate() {

    this.personService.createPerson(this.createdPerson).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));

    this.creating = false;
  }

}
