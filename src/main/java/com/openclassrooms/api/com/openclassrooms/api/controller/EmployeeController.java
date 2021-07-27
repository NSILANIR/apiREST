package com.openclassrooms.api.com.openclassrooms.api.controller;

import com.openclassrooms.api.com.openclassrooms.api.model.Employee;
import com.openclassrooms.api.com.openclassrooms.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Read - Get all employees
     * @return - An Iterable object of Employee full filled
     */
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees(){

        return employeeService.getEmployees();
    }

    /**
     * Create - Add a new employee
     * @param employee An object employee
     * @return The employee object saved
     */
    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee){

        return employeeService.saveEmployee(employee);
    }

    /**
     * Read - Get one employee
     * @param id The id of the employee
     * @return An Employee object full filled
     */
    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") final Long id){

        Optional<Employee> employee = employeeService.getEmployee(id);

        if (employee.isPresent()){

            return employee.get();
        } else{

            return null;
        }
    }

    /**
     * Update - Update an existing employee
     * @param id - The id of the employee to update
     * @param employee - The employee object updated
     * @return
     */
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") final Long id,@RequestBody Employee employee){

        Optional<Employee> emp = employeeService.getEmployee(id);

        if(emp.isPresent()){

            Employee currentEmployee = emp.get();

            String firstName = employee.getFirstName();

            if(firstName != null){
                currentEmployee.setFirstName(firstName);
            }

            String lastName = employee.getLastName();

            if(lastName != null){
                currentEmployee.setLastName(lastName);
            }

            String mail = employee.getMail();

            if(mail != null){
                currentEmployee.setMail(mail);
            }

            String password = employee.getPassword();

            if(password != null){
                currentEmployee.setPassword(password);
            }

            return currentEmployee;

        }else{
            return null;
        }
    }

    @DeleteMapping("/employee/{is}")
    public void deleteEmployee(@PathVariable("id") final Long id){
        employeeService.deleteEmployee(id);
    }


}

