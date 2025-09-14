package com.myprojects.pauldirac.controllers;

import com.myprojects.pauldirac.dao.StudentDAOImpl;
import com.myprojects.pauldirac.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    StudentDAOImpl studentDAOImpl;

    @Autowired
    public StudentRestController(StudentDAOImpl studentDAOImpl) {
        this.studentDAOImpl = studentDAOImpl;
    }

    @GetMapping("/students")
    public List<Student> getListOfStudents() {
        List<Student> myStudents = new ArrayList<Student>();
        myStudents.add(new Student("Ilja", "Goushcha","ilja@yahoo.com"));
        myStudents.add(new Student("Mario", "Cook","mario@gmail.com"));
        myStudents.add(new Student("Ben", "Shapiro","ben@gmail.com"));
        return myStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {
        Student response = studentDAOImpl.findById(studentId);
        System.out.println("response: " + response);
        if (response == null ) {
            throw new StudentNotFoundException("student with id=" + studentId + " does not exist.");
        }
        return response;
    }

}
