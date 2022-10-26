package ro.tuc.ds2022.dtos.builders;

import org.springframework.stereotype.Component;
import ro.tuc.ds2022.dtos.PersonDTO;
import ro.tuc.ds2022.entities.Person;

@Component
public class PersonBuilder {

    private PersonBuilder() {
    }

    public PersonDTO toDTO(Person person) {
        return new PersonDTO(person.getId(), person.getName(), person.getRole(), person.getUsername(), person.getPassword());
    }


    public Person toEntity(PersonDTO personDTO) {
        Person person = new Person();

        person.setName(personDTO.getName());
        person.setUsername(personDTO.getUsername());
        person.setPassword(personDTO.getPassword());

        if (personDTO.getId() == null)
            person.setId(0);
        else
            person.setId(personDTO.getId());

        if (personDTO.getRole() == null)
            person.setRole(0);
        else
            person.setRole(personDTO.getRole());

        return person;
    }

}
