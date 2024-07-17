package com.example.demo.team;

import com.example.demo.employee.Employee;
import com.example.demo.employee.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    public  TeamRepo teamRepo;
    @Autowired
    public EmployeeRepo employeeRepo;


    public List<Team> getTeams() {
        return teamRepo.findAll();
    }

    public Team getTeamWithId(Long teamId) {
        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new IllegalStateException("Team with given id does not exist"));

        return team;
    }

    public void addNewTeam(Team team) {
        Optional<Team> teamOptional = teamRepo.findTeamByTeamName(team.getTeamName());
        System.out.println("the err is : ");
        System.out.println(team);
        System.out.println(team.getTeamName());
        if(teamOptional.isPresent()) {
            throw new IllegalStateException("Name already taken");
        }

        teamRepo.save(team);
    }

    public void deleteTeam(Long id) {

        boolean findTeam = teamRepo.existsById(id);
        // Optional<Employee> findEmp = employeeRepo.findEmployeeByEmployeeId(id);
        if(!findTeam) {
            throw new IllegalStateException("Team with given id is not present");
        }

        List<Employee> findEmp = employeeRepo.findEmployeeByEmployeeTeam(id);
        // Optional<Employee> findEmp = employeeRepo.findEmployeeByEmployeeId(id);
        if(!findEmp.isEmpty()) {
            throw new IllegalStateException("Employee with given team id are present");
        }
        teamRepo.deleteById(id);
    }

    @Transactional
    public void updateTeam(Long teamId, String name, String description) {
        System.out.println(name);
        System.out.println(description);
        System.out.println("this method get called");
        Team team = teamRepo.findTeamByTeamId(teamId);

        String oldName = team.getTeamName();

        if(!Objects.equals(oldName, name)) {
            Optional<Team> check = teamRepo.findTeamByTeamName(name);
            if(check.isPresent()) {
                throw new IllegalStateException("Name already taken");
            }
        }

        if(name !=  null && name.length() > 0
                && description !=  null && description.length() > 0) {
            team.setTeamName(name);
            team.setTeamDescription(description);
            teamRepo.save(team);
        }
        System.out.println("success");
    }
}