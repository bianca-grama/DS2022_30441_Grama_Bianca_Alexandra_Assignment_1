package ro.tuc.ds2022.dtos.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ro.tuc.ds2022.entities.Person;
import ro.tuc.ds2022.repositories.PersonRepository;
import ro.tuc.ds2022.services.PasswordEncryptor;

@Component
public class PersonValidator {

    @Autowired
    PersonRepository personRepository;

    @Value("${salt}")
    private String encryptionSalt;

    public boolean isValid(Person person) {
        return (person.getUsername() != null);
    }

    private boolean isUsernameInDb(String username) {
        Person p = personRepository.findByUsername(username);
        return (p != null);
    }

    public boolean isValidForSave(Person person) {
        //person should be valid and username should not be in db
        return isValid(person) && !isUsernameInDb(person.getUsername());
    }

    public boolean isValidForUpdate(Person oldPerson, Person newPerson) {
        //person should be valid; either his username is unchanged, or it is new but not already in the db
        if (isValid(newPerson)) {
            if (oldPerson.getUsername().equals(newPerson.getUsername())) {
                return true;
            } else {
                return !isUsernameInDb(newPerson.getUsername());
            }

        }
        return false;
    }

    public boolean isValidLogin(Person person) {
        //person should be valid, username should be in db and password should match
        boolean isValid = false;

        if (!isValid(person)) {
            return false;
        }
        if (isUsernameInDb(person.getUsername())) {
            Person personInDatabase = personRepository.findByUsername(person.getUsername());
            isValid = PasswordEncryptor.verifyUserPassword(person.getPassword(), personInDatabase.getPassword(), encryptionSalt);
        }
        return isValid;
    }


}
