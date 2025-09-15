package com.myprojects.pauldirac.service;

import com.myprojects.pauldirac.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);

}
