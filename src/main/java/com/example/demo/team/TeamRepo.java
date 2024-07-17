package com.example.demo.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepo extends JpaRepository<Team,Long> {

    @Query
    Optional<Team> findTeamByTeamName(String name);

   // @Query
   // Optional<Team> findTeamByTeamId(Long id);

    @Query
    Team findTeamByTeamId(Long id);

}