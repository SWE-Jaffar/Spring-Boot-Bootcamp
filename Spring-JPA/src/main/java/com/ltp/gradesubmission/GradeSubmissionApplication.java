package com.ltp.gradesubmission;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ltp.gradesubmission.entity.Student;
import com.ltp.gradesubmission.repository.StudentRepository;

@SpringBootApplication
public class GradeSubmissionApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(GradeSubmissionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Student[] students = new Student[] {
            new Student(1L, "Harry Potter", LocalDate.of(1980, 7, 31)),
            new Student(2L, "Ron Weasley", LocalDate.of(1980, 3, 1)),
            new Student(3L, "Hermione Granger", LocalDate.of(1979, 9, 19)),
            new Student(4L, "Neville Longbottom", LocalDate.of(1980, 7, 30))
        };

        for (Student student : students) {
            try {
                studentRepository.save(student);
            } catch (Exception e) {
                System.err.println("Failed to save student: " + student.getName());
                e.printStackTrace(); // Print or handle the exception as needed
            }
        }
    }
}
