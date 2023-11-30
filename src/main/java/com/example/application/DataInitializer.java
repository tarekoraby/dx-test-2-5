package com.example.application;

import com.example.application.backend.Employee;
import com.example.application.backend.EmployeeRepository;
import com.example.application.backend.Team;
import com.example.application.backend.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer {

    List<String> employeeNames = List.of("John", "Jane", "Mary", "Joe", "Suzanne", "George", "Amanda", "Hans", "Ulrich",
            "Emma", "Gabriel", "Eva", "Ursula", "Uwe", "Udo", "Ulla");
    List<String> teamNames = List.of("Sales", "Marketing", "Human Resources", "Finance", "Research", "Development",
            "Customer Support", "IT", "Legal", "Procurement", "Logistics", "Production", "Quality Management",
            "Facility Management", "Executive Board", "Shareholder Meeting", "General Assembly");

    private final List<Team> teams = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();

    @Bean
    CommandLineRunner initDatabase(TeamRepository teamRepository, EmployeeRepository employeeRepository) {
        return args -> {
            createAndSaveTeams(teamRepository);
            createAndSaveEmployees(teamRepository, employeeRepository);
        };
    }

    private void createAndSaveEmployees(TeamRepository teamRepository, EmployeeRepository employeeRepository) {
        for (int i=0; i<1000; i++){
            String firstName = employeeNames.get((int) (Math.random() * employeeNames.size()));
            String lastName = employeeNames.get((int) (Math.random() * employeeNames.size()));
            String name = firstName + " " + lastName;
            String email = name.toLowerCase() + "@example.com";

            Employee employee = employeeRepository.save(new Employee(name, email, null));

            int maxNumberOfTeams = (int) (Math.random() * 4) + 1;
            Set<Team> employeeTeams = new HashSet<>();
            for (int j=0; j<maxNumberOfTeams; j++){
                // get a random team from the repository
                Team team = this.teams.get((int) (Math.random() * this.teams.size()));
                if (team != null){
                    // add the employee to the team
                    employeeTeams.add(team);
                    team.getEmployees().add(employee);
                }
            }

            employees.add(employee);
        }
        teamRepository.saveAll(teams);
    }

    private void createAndSaveTeams(TeamRepository teamRepository) {
        for (String teamName : teamNames) {
            String description = "This is the " + teamName + " team.";
            String email = teamName.toLowerCase().replace(" ", "-") + "@example.com";
            String slackChannel = "#" + teamName.toLowerCase().replace(" ", "-");
            String githubRepo = "github.com/" + teamName.toLowerCase().replace(" ", "-");
            teams.add(new Team(teamName, description, email, slackChannel, githubRepo, new ArrayList<>()));
        }
        teamRepository.saveAll(teams);
        teamRepository.flush();
    }
/*
    public void initTeams() {
        teams = new ArrayList<>();
        // Create teams from the team names list
        for (String teamName : teamNames) {
            String description = "This is the " + teamName + " team.";
            String email = teamName.toLowerCase().replace(" ", "-") + "@example.com";
            String slackChannel = "#" + teamName.toLowerCase().replace(" ", "-");
            String githubRepo = "github.com/" + teamName.toLowerCase().replace(" ", "-");
            Team team = new Team(teamName, description, email, slackChannel, githubRepo, new ArrayList<>());
            teams.add(team);
        }
    }

    public void initEmployees(EmployeeRepository employeeRepository, TeamRepository teamRepository) {
        // Create 1000 Employees randomly selected from the names list
        for (int i=0; i<1000; i++){
            String firstName = employeeNames.get((int) (Math.random() * employeeNames.size()));
            String lastName = employeeNames.get((int) (Math.random() * employeeNames.size()));
            String name = firstName + " " + lastName;
            String email = name.toLowerCase() + "@example.com";
            Employee employee = new Employee(name, email);
            employee = employeeRepository.save(employee);

            // select a random number of teams between 1 and 3
            int numberOfTeams = (int) (Math.random() * 3) + 1;
            for (int j=0; j<numberOfTeams; j++){
                // get a random team from the repository
                Team team = teams.get((int) (Math.random() * teams.size()));
                if (team != null){
                    // add the employee to the team
                    team.getEmployees().add(employee);
                    employee.getTeams().add(team);
                }
            }
            employeeRepository.save(employee);
        }
    }

 */
}
