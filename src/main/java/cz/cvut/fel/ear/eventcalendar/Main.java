package cz.cvut.fel.ear.eventcalendar;

import cz.cvut.fel.ear.eventcalendar.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("application is started");
        /*Student student = new Student();
        student.setFirstName("Alik");
        student.setLastName("Novak");
        student.setUsername("alinovak");
        student.setStudentId(1337);
        System.out.println(student.toString());*/
        SpringApplication.run(Main.class, args);
    }
}
