package com.example.application.services;

import com.example.application.backend.EmployeeRepository;
import com.example.application.backend.TeamRepository;
import com.example.application.dtos.TeamDto;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.BrowserCallable;
import dev.hilla.Nullable;
import dev.hilla.crud.CrudService;
import dev.hilla.crud.filter.Filter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@BrowserCallable
@AnonymousAllowed
@Service
public class TaskOneAndTwoService implements CrudService<TeamDto, Long> {

    private TeamRepository teamRepository;
    private EmployeeRepository employeeRepository;

    public TaskOneAndTwoService(TeamRepository teamRepository, EmployeeRepository employeeRepository) {
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public @Nullable TeamDto save(TeamDto value) {
        teamRepository.findById(value.id()).ifPresent(team -> {
            team.setName(value.name());
            team.setDescription(value.description());
            team.setEmail(value.email());
            team.setSlackChannel(value.slackChannel());
            team.setGithubRepo(value.githubRepo());
            teamRepository.save(team);
        });
        return value;
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }

    @Override
    public TeamDto get(Long id) {
        return teamRepository.findByIdWithEmployees(id).map(TeamDto::from).orElse(null);
    }

    @Override
    public List<TeamDto> list(Pageable pageable, @Nullable Filter filter) {
        return teamRepository.findAllWithEmployees(pageable).stream().map(TeamDto::from).toList();
    }
}
