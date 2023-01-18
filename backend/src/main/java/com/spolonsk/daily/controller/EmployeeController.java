package com.spolonsk.daily.controller;

import com.spolonsk.daily.exception.ResourceNotFoundException;
import com.spolonsk.daily.model.Employee;
import com.spolonsk.daily.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
//@CrossOrigin(origins = "*", allowedHeaders = "*",methods = )
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    //get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //create employee
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    //get employee by ID
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.
                findById(id).orElseThrow(
                        ()->new ResourceNotFoundException
                                ("Employee not found " + id));
        return ResponseEntity.ok(employee);

    }

    //update existing employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,@RequestBody Employee newEmployee) {
        Employee employee = employeeRepository.
                findById(id).orElseThrow(
                        () -> new ResourceNotFoundException
                                ("Employee not found " + id));
        employee.setFirstName(newEmployee.getFirstName());
        employee.setLastName(newEmployee.getLastName());
        employee.setEmailId(newEmployee.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
        //delete employee
        @DeleteMapping("/employees/{id}")
        public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
            Employee employee = employeeRepository.
                    findById(id).orElseThrow(
                            ()->new ResourceNotFoundException
                                    ("Employee not found " + id));

            employeeRepository.delete(employee);
            Map<String,Boolean> response = new HashMap<>();
            response.put("deleted",Boolean.TRUE);
            return ResponseEntity.ok(response);

        }
}
