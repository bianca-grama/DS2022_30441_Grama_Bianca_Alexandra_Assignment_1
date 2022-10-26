package ro.tuc.ds2022.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ro.tuc.ds2022.Ds2022TestConfig;
import ro.tuc.ds2022.dtos.PersonDTO;

import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.util.List;
import java.util.UUID;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/test-sql/create.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:/test-sql/delete.sql")
public class PersonServiceIntegrationTests extends Ds2022TestConfig {

    @Autowired
    PersonService personService;

    @Test
    public void testGetCorrect() {
        List<PersonDTO> personDTOList = personService.findPersons();
        assertEquals("Test Insert Person", 1, personDTOList.size());
    }

    @Test
    public void testInsertCorrectWithGetById() {
        PersonDTO p = new PersonDTO(1, "Somebody", 1);
        Integer insertedID = personService.insert(p);

        PersonDTO insertedPerson = new PersonDTO(insertedID, p.getName(),p.getRole());
        PersonDTO fetchedPerson = personService.findPersonById(insertedID);

        assertEquals("Test Inserted Person", insertedPerson, fetchedPerson);
    }

    @Test
    public void testInsertCorrectWithGetAll() {
        PersonDTO p = new PersonDTO(1, "Somebody", 1);
        personService.insert(p);

        List<PersonDTO> personDTOList = personService.findPersons();
        assertEquals("Test Inserted Persons", 2, personDTOList.size());
    }
}
