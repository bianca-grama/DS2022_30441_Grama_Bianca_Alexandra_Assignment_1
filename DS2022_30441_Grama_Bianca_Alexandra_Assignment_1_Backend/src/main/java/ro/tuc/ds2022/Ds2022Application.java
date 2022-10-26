package ro.tuc.ds2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.validation.annotation.Validated;

import java.util.TimeZone;

@SpringBootApplication
@Validated
public class Ds2022Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Ds2022Application.class);
    }

    public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Ds2022Application.class, args);
    }
}
