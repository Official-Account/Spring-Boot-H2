package com.h2.controller;

import com.h2.dao.EmployeeDao;
import com.h2.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        try {

        Employee saveEmployee = employeeDao.save(employee);
        return new ResponseEntity<>( saveEmployee, HttpStatus.CREATED);
        }catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

   @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        try{
       List<Employee> employees = employeeDao.findAll();
       if (!employees.isEmpty())
        return new ResponseEntity<>(employees, HttpStatus.OK);
       else
           return new ResponseEntity<>(employees, HttpStatus.NOT_FOUND);
        }catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{empId}")
    public  ResponseEntity<Employee> getEmployee(@PathVariable  Integer empId){
        try{
        Employee employee = employeeDao.findById(empId).get();
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{empId}")
    public  ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable Integer empId){
        try {
            if (employeeDao.existsById(empId)) {
                employee.setId(empId);
                Employee updateEmployee = employeeDao.save(employee);
                return new ResponseEntity<>(employee, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
            }
         }catch (Exception ex) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @GetMapping("/emp/{dept}")
    public  ResponseEntity<List<Employee>> getEmployeeByDepartment( @PathVariable String dept){
        try {
                return new ResponseEntity<>(employeeDao.findByDepartment(dept), HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{empId}")
    public  ResponseEntity<String> deleteEmployee(@PathVariable Integer empId){
        try {
        if(employeeDao.existsById(empId)){
            employeeDao.deleteById(empId);
            return new ResponseEntity<>("Employee deleted Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Employee deleted Successfully", HttpStatus.NOT_FOUND);
        }
        }catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
