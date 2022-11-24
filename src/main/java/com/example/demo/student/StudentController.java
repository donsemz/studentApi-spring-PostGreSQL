package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {

    // initialize service to be called by this controller
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;// not use new StudentService to inject. use Dependency injection
    }
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path ="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentid){
        studentService.deleteStudent(studentid);
    }

    @PutMapping(path ="{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentid,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        studentService.updateStudent(studentid, name, email);
    }

}