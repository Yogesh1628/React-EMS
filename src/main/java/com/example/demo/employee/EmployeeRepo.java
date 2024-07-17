package com.example.demo.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    @Query
    Optional<Employee> findEmployeeByEmployeeEmail(String email);

    @Query
    Employee findEmployeeByEmployeeId(Long id);

    @Query
    List<Employee> findEmployeeByEmployeeTeam(Long teamId);
}