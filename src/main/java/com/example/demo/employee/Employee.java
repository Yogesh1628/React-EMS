package com.example.demo.employee;
//package com.postgresql.demo4.employee;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@Entity
@Table(name = "employee")
@Getter
public class Employee {


    @Getter
    @Id
    @SequenceGenerator(
            name="employee_sequence",
            sequenceName="employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )

    private Long employeeId;
    private String employeeFullName;
    private String employeeEmail;
    private String employeeJobTitle;
    private Long employeeTeam;
    private String employeeImage;

    public Employee() {

    }
    public Employee(Long employeeId, String employeeFullName,
                    String employeeEmail, String employeeJobTitle, Long employeeTeam, String employeeImage) {
        this.employeeId = employeeId;
        this.employeeFullName = employeeFullName;
        this.employeeEmail = employeeEmail;
        this.employeeJobTitle = employeeJobTitle;
        this.employeeTeam = employeeTeam;
        this.employeeImage = employeeImage;
    }

    public Employee(String employeeFullName, String employeeEmail,
                    String employeeJobTitle, Long employeeTeam, String employeeImage) {
        this.employeeFullName = employeeFullName;
        this.employeeEmail = employeeEmail;
        this.employeeJobTitle = employeeJobTitle;
        this.employeeTeam = employeeTeam;
        this.employeeImage = employeeImage;
    }

    @Override
    public String toString() {
        return "Success";
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
    public void setEmployeeImage(String employeeImage) {
        this.employeeImage = employeeImage;
    }

    public String getEmployeeJobTitle() {
        return employeeJobTitle;
    }
    public String getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeJobTitle(String employeeJobTitle) {
        this.employeeJobTitle = employeeJobTitle;
    }

    public Long getEmployeeTeam() {
        return employeeTeam;
    }

    public void setEmployeeTeam(Long employeeTeam) {
        this.employeeTeam = employeeTeam;
    }
}