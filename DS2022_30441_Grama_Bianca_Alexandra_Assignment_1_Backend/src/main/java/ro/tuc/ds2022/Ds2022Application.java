package ro.tuc.ds2022;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import ro.tuc.ds2022.dtos.PersonDTO;
import ro.tuc.ds2022.services.PersonService;

import java.util.TimeZone;

@SpringBootApplication
@Validated
public class Ds2022Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Ds2022Application.class);
    }

    @Bean
    public CommandLineRunner run(PersonService personService) {
        return args -> {
            if (personService.getAdminUser() == null) {
                personService.insert(new PersonDTO(0, "admin", 1, "admin", "admin"));
            }
        };
    }

    public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Ds2022Application.class, args);
    }
}
