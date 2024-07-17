package com.example.demo.employee;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;


    @Autowired
    public  EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<Employee>> getEmployees() {
        System.out.println("method called again and again");
        employeeService.getEmployees();
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @GetMapping(path = "/checkMail/{value}")
    public boolean checkEmail(@PathVariable("value") String email) {
        return employeeService.checkEmail(email);
    }

    @GetMapping(path = "/get/{employeeId}")
    public Employee getEmployeeWithId(@PathVariable("employeeId") Long id) {
        return employeeService.getEmployeeWithId(id);
    }

    @GetMapping(path = "/getWithTeamId/{employeeId}")
    public List<Employee> getEmployeeWithTeamId(@PathVariable("employeeId") Long id) {
        return employeeService.getEmployeeWithTeamId(id);
    }

    @PostMapping(path = "/insert")
    public void registerNewEmployee(@RequestBody Employee employee) {
        System.out.println("emp create is called");
        employeeService.addNewEmployee(employee);
    }

//    @PostMapping(path = "/insert")
//    public void registerNewEmployee(@RequestParam String employeeFullName,
//                                    @RequestParam String employeeEmail,
//                                    @RequestParam String employeeJobTitle,
//                                    @RequestParam Long employeeTeam,
//                                    @RequestParam MultipartFile employeeImage) throws IOException  {
//        System.out.println("emp create is called");
//        employeeService.addNewEmployee(employeeFullName, employeeEmail, employeeJobTitle, employeeTeam, employeeImage);
//    }

    @DeleteMapping(path = "/delete/{employeeID}")
    public void deleteEmployee(@PathVariable("employeeID") Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping(path = "/update/{employeeId}")
    public void updateEmployee(
            @PathVariable("employeeId") Long employeeID,
            @RequestBody Employee employee) {
        System.out.println("emp put method called");
        employeeService.updateEmployee(employeeID, employee);
    }
}