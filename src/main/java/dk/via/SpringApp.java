package dk.via;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dk.via.course_assignment_3", "dk.via.shared"})
@ComponentScan(basePackages = {"dk.via.course_assignment_2", "dk.via.shared"})
@ComponentScan(basePackages = {"dk.via.course_assignment_4", "dk.via.shared"})
public class SpringApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringApp.class);
        app.run(args);
    }
}
