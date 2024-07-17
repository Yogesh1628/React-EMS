package com.example.demo.employee;

import com.example.demo.team.TeamRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    // Do Proper return Statements, remove voids
    //Encapsulate Errors in the Response Class
    // Dto To entity
    @Autowired
    public TeamRepo teamRepo;
    @Autowired
    public EmployeeRepo employeeRepo;


    @Value("${file.upload-dir}")
    private String uploadDir;
//
//    @Value()
//    private String serverAddress;
//
//    private String serverPort;


    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeWithId(Long employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException("Employee with given id does not exist"));

        return employee;
    }
    public List<Employee> getEmployeeWithTeamId(Long teamId) {
        List<Employee> compList = employeeRepo.findEmployeeByEmployeeTeam(teamId);
        System.out.println(compList.size());
        return compList;
    }

    public boolean checkEmail(String email) {
        Optional<Employee> emp = employeeRepo.findEmployeeByEmployeeEmail(email);
        if(emp.isPresent()) {
            throw new IllegalStateException("Email already present");
        }
        else {
            return true;
        }
    }

    public void addNewEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepo.findEmployeeByEmployeeEmail(employee.getEmployeeEmail());
        if(employeeOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }


        employeeRepo.save(employee);
    }

//    public void addNewEmployee(String name, String email, String job, Long team, MultipartFile image) throws IOException {
//        Optional<Employee> employeeOptional = employeeRepo.findEmployeeByEmployeeEmail(email);
//        if(employeeOptional.isPresent()) {
//            throw new IllegalStateException("Email already taken");
//        }
//
//        String fileName = image.getOriginalFilename();
//        Path path = Paths.get(uploadDir + fileName);
//        Files.createDirectories(path.getParent());
//        Files.write(path, image.getBytes());
//        String imagePath = "http://" + "localhost:8080" + "/" + path.toString().replace("\\", "/");
//
//        Employee employee = new Employee();
//        employee.setEmployeeFullName(name);
//        employee.setEmployeeEmail(email);
//        employee.setEmployeeJobTitle(job);
//        employee.setEmployeeTeam(team);
//        employee.setEmployeeImage(imagePath);
//        employeeRepo.save(employee);
//    }
    public void deleteEmployee(Long id) {

        boolean findEmp = employeeRepo.existsById(id);
        // Optional<Employee> findEmp = employeeRepo.findEmployeeByEmployeeId(id);
        if(!findEmp) {
            throw new IllegalStateException("Employee with given id is not present");
        }
        employeeRepo.deleteById(id);
    }

    @Transactional
    public void updateEmployee(Long employeeId, Employee employee) {
        String fullName = employee.getEmployeeFullName();
        String email = employee.getEmployeeEmail();
        long teamId = employee.getEmployeeTeam();

        Employee emp = employeeRepo.findEmployeeByEmployeeId(employeeId);
        String oldEmail = emp.getEmployeeEmail();

        if(!Objects.equals(oldEmail, email)) {
            Optional<Employee> employeeOptional = employeeRepo.findEmployeeByEmployeeEmail(email);

            if(emp.getEmployeeEmail() != employee.getEmployeeEmail() && employeeOptional.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }
        }
        boolean findTeam = teamRepo.existsById(teamId);
        if(findTeam == false) {
            throw new IllegalStateException("First create team, then add employee");
        }
        Employee currEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException("Employee with given id does not exist"));

        if(fullName !=  null && fullName.length() > 0 && email !=  null && email.length() > 0) {
            employee.setEmployeeFullName(fullName);
            employee.setEmployeeEmail(email);
            employee.setEmployeeTeam(teamId);
            employeeRepo.save(employee);
        }

    }
}