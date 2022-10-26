package ro.tuc.ds2022.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2022.dtos.PersonDTO;
import ro.tuc.ds2022.services.PersonService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public ResponseEntity<String> insertPerson(@RequestBody PersonDTO personDTO) {
        Integer personID = personService.insert(personDTO);
        if(personID != null){
            return new ResponseEntity<>(String.format("Inserted person with ID %s.", personID), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Username already in use", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") Integer personId) {
        PersonDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PersonDTO>> getPersons() {
        List<PersonDTO> dtos = personService.findPersons();
        for (PersonDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PersonController.class)
                    .getPerson(dto.getId())).withRel("person");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updatePerson(@RequestParam(name = "id") Integer id, @RequestBody PersonDTO personDTO) {
        PersonDTO updatedPerson = personService.updatePerson(id, personDTO);
        if (updatedPerson != null) {
            return new ResponseEntity<>("Person updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Person update failed.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deletePerson(@RequestParam(name = "id") Integer id) {
        boolean success = personService.deletePersonById(id);
        if (success) {
            return new ResponseEntity<>("Delete successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Delete failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<PersonDTO> login(@RequestBody PersonDTO personDTO) {
        PersonDTO personLoggedIn = personService.login(personDTO);
        if (personLoggedIn != null) {
            return new ResponseEntity<>(personDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
