package com.example.application.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>,
        JpaSpecificationExecutor<Employee> {

    @Query("SELECT e FROM Employee e JOIN FETCH e.teams WHERE e.id = :id")
    Optional<Employee> findByIdWithTeams(Long id);
}
