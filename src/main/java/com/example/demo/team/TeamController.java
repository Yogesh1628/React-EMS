package com.example.demo.team;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/team")
@CrossOrigin(origins = "*")
public class TeamController {
    private static final Logger log = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;


    @Autowired
    public  TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(path = "/get")
    public List<Team> getTeams() {
        return teamService.getTeams();
    }

    @GetMapping(path = "/get/{teamId}")
    public Team getTeamWithId(@PathVariable("teamId") Long id) {
        return teamService.getTeamWithId(id);
    }

    @PostMapping(path = "/insert")
    public void registerNewTeam(@RequestBody Team team) {
        System.out.println(team);
        teamService.addNewTeam(team);
    }

    @DeleteMapping(path = "/delete/{teamID}")
    public void deleteTeam(@PathVariable("teamID") Long id) {
        teamService.deleteTeam(id);
    }

    @PutMapping(path = "/update/{teamId}")
    public void updateTeam(
            @PathVariable("teamId") Long teamID,
            @RequestBody Team team) {
        teamService.updateTeam(teamID, team.getTeamName(), team.getTeamDescription());
    }
}