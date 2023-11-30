package com.example.application.backend;

import dev.hilla.crud.GetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TeamRepository extends JpaRepository<Team, Long>,
        JpaSpecificationExecutor<Team> {

        @Query("SELECT t FROM Team t JOIN FETCH t.employees WHERE t.id = :id")
        Optional<Team> findByIdWithEmployees(Long id);

        @Query("SELECT t FROM Team t JOIN FETCH t.employees")
        Page<Team> findAllWithEmployees(Pageable pageable);

        // Taking the team name as a parameter, return the team with the employees
        @Query("SELECT t FROM Team t JOIN FETCH t.employees WHERE t.name = :name")
        Optional<Team> findByNameWithEmployees(String name);


}
