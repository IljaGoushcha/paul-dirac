package com.myprojects.pauldirac.dao;

import com.myprojects.pauldirac.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Updates and modification require transactions
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> myQuery = entityManager.createQuery("FROM Student", Student.class);
        return myQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> myQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);
        myQuery.setParameter("theData", lastName);
        return myQuery.getResultList();
    }

    @Override
    @Transactional
    public int updateAllLastNames(String lastName) {
        Query myQuery = entityManager.createQuery("UPDATE Student SET lastName=:theData");
        myQuery.setParameter("theData", lastName);
        int numberOfUpdatedRows = myQuery.executeUpdate();
        return numberOfUpdatedRows;
    }

    @Override
    @Transactional
    public Student updateLastNameById(int id, String lastName) {
        Student myStudent = entityManager.find(Student.class, id);
        myStudent.setLastName(lastName);
        if (myStudent != null) {
            entityManager.merge(myStudent);
        }
        return myStudent;
    }

    @Override
    public void update(Student student) {

    }

}
