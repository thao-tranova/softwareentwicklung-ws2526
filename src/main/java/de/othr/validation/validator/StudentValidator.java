package de.othr.validation.validator;

import de.othr.validation.model.Student;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StudentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Student student = (Student) target;
        if (student.getBirthDate() != null) {
            long age = ChronoUnit.YEARS.between(student.getBirthDate(), LocalDate.now());
            System.out.println("Age: " + age);
            if (age < 16) {
                errors.rejectValue("birthDate", null, null, "Ein Studierende muss mindestens 16 Jahre alt sein.");
            }
        }
    }
}
