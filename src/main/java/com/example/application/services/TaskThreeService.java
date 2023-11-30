package com.example.application.services;

import com.example.application.backend.EmployeeRepository;
import com.example.application.dtos.EmployeeDto;
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
public class TaskThreeService implements CrudService<EmployeeDto, Long> {
    private EmployeeRepository employeeRepository;

    public TaskThreeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public @Nullable EmployeeDto save(EmployeeDto value) {
        employeeRepository.findById(value.id()).ifPresent(employee -> {
            employee.setName(value.name());
            employee.setEmail(value.email());
            employeeRepository.save(employee);
        });
        return value;
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto get(Long id) {
        return employeeRepository.findById(id).map(EmployeeDto::from).orElse(null);
    }

    @Override
    public List<EmployeeDto> list(Pageable pageable, @Nullable Filter filter) {
        return employeeRepository.findAll(pageable).stream().map(EmployeeDto::from).toList();
    }
}
