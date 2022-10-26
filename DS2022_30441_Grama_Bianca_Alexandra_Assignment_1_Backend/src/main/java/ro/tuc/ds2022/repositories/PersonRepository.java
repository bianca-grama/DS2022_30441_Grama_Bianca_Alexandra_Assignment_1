package ro.tuc.ds2022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2022.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByUsername(String username);
}
