package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
            Student peter = new Student(
                    "Peter",
                    LocalDate.of(2000, Month.JANUARY,9),
                    "psycho@gmail.com"
            );
            Student mary = new Student(
                    "Mary",
                    LocalDate.of(1999, Month.FEBRUARY,19),
                    "mary@gmail.com"
            );

            repository.saveAll(
                    List.of(peter,mary)
            );
        };
    }
}
