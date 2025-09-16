package com.myprojects.pauldirac.controllers;

import com.myprojects.pauldirac.entity.Employee;
import com.myprojects.pauldirac.service.EmployeeService;
import com.myprojects.pauldirac.utils.PatchObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    EmployeeService employeeService;
    PatchObjectUtil patchObjectUtil;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, PatchObjectUtil patchObjectUtil) {
        this.employeeService = employeeService;
        this.patchObjectUtil = patchObjectUtil;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        // Just in case an actual ID was passed, this will force it to be recreated by DB
        employee.setId(0);
        Employee newEmployee = employeeService.save(employee);
        return newEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.save(employee);
        return updatedEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteById(employeeId);
        return ResponseEntity.ok("Employee (id=" + employeeId + ") deleted successfully.");
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> patchPayload) {

        // Patch will update just one column in table or field in entity
        Employee originalEmployee = employeeService.findById(employeeId);
        if (originalEmployee == null) {
            throw new RuntimeException("Employee (id=" + employeeId + ") not found.");
        }

        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Employee id (id=" + employeeId + ") not allowed in request body.");
        }

        Employee updatedEmployee = patchObjectUtil.apply(patchPayload, originalEmployee);

        Employee patchedEmployee = employeeService.save(updatedEmployee);
        return patchedEmployee;
    }

}
