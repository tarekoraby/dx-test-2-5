package com.example.application.dtos;

import com.example.application.backend.Team;

import java.util.Set;
import java.util.stream.Collectors;

public record TeamDto(
        Long id,
        String name,
        String description,
        String email,
        String slackChannel,
        String githubRepo,

        Set<EmployeeDto> employees){


    public static TeamDto from(Team team){
        return new TeamDto(
                team.getId(),
                team.getName(),
                team.getDescription(),
                team.getEmail(),
                team.getSlackChannel(),
                team.getGithubRepo(),
                team.getEmployees().stream().map(EmployeeDto::from).collect(Collectors.toSet())
        );
    }
}
