package com.myprojects.pauldirac.dao;

import com.myprojects.pauldirac.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    int updateAllLastNames(String lastName);

    Student updateLastNameById(int id, String lastName);

    void update(Student student);
}
