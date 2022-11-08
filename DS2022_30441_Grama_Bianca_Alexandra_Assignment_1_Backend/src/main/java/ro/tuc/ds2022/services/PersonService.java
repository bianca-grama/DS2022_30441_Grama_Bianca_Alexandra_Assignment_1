package ro.tuc.ds2022.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.tuc.ds2022.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2022.dtos.PersonDTO;
import ro.tuc.ds2022.dtos.builders.PersonBuilder;
import ro.tuc.ds2022.dtos.validators.PersonValidator;
import ro.tuc.ds2022.entities.Person;
import ro.tuc.ds2022.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    PersonValidator personValidator;
    
    @Autowired
    PersonBuilder personBuilder;

    @Value("${salt}")
    private String encryptionSalt;

    public List<PersonDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(personBuilder::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findPersonById(Integer id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return personBuilder.toDTO(prosumerOptional.get());
    }

    public Integer insert(PersonDTO personDTO) {
        Person person = personBuilder.toEntity(personDTO);
        if (personValidator.isValidForSave(person)){

            String encryptedPassword = PasswordEncryptor.generateSecurePassword(person.getPassword(), encryptionSalt);
            person.setPassword(encryptedPassword);

            person = personRepository.save(person);
            LOGGER.debug("Person with id {} was inserted in db", person.getId());
            return person.getId();
        } else {
            LOGGER.error("Invalid person data");
            return null;
        }
    }

    public PersonDTO updatePerson(Integer id, PersonDTO newPerson) {

        Optional<Person> personInDb = personRepository.findById(id);
        if (!personInDb.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        } else {
            if (personValidator.isValidForUpdate(personInDb.get(), personBuilder.toEntity(newPerson))){
                personInDb.get().setUsername(newPerson.getUsername());
                personInDb.get().setName(newPerson.getName());
                personInDb.get().setRole(newPerson.getRole());

                Person updatedPerson = personRepository.save(personInDb.get());
                LOGGER.debug("Person with id {} was updated", id);
                return personBuilder.toDTO(updatedPerson);
            }
        }
        return null;
    }

    public boolean deletePersonById(Integer id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            personRepository.delete(person.get());
            return true;
        }
        return false;
    }

    public PersonDTO login(PersonDTO personDTO) {
        Person person = personBuilder.toEntity(personDTO);
        if (personValidator.isValidLogin(person)) {
            Person personLoggedIn = personRepository.findByUsername(person.getUsername());
            return personBuilder.toDTO(personLoggedIn);
        }
        return null;
    }

    public Person getAdminUser(){
        List<Person> personList = personRepository.findAll();
        for(Person person : personList){
            if(person.getRole() == 1){
                return person;
            }
        }
        return null;
    }

}
