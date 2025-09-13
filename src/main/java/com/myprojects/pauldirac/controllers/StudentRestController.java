package com.myprojects.pauldirac.controllers;

import com.myprojects.pauldirac.dao.StudentDAOImpl;
import com.myprojects.pauldirac.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    @Autowired
    StudentDAOImpl studentDAOImpl;

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
        return response;
    }

}
