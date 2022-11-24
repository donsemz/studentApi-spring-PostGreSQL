package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
//@Component - same as using @Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    //service definition
    public List<Student> getStudents(){
      return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Student Email Already Taken!!");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentid) {
        boolean exists = studentRepository.existsById(studentid);
        if (!exists) {
            throw new IllegalStateException("Student with Id "+studentid+" doesnt exist!");
        }
        studentRepository.deleteById(studentid);
    }
    @Transactional
    public void updateStudent(Long studentid, String name, String email) {
        Student student = studentRepository.findById(studentid).orElseThrow(()->new IllegalStateException(
                "Student with id "+studentid+"doesnt exist"
        ));

        if (name != null &&
                name.length()>0 && !Objects.equals(student.getName(),name)) {
            student.setName(name);
        }

        if (email != null &&
                email.length()>0 && !Objects.equals(student.getEmail(),email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Student Email "+email+" is already taken");
            }
            student.setEmail(email);
        }
    }
}

