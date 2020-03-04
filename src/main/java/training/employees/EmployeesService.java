package training.employees;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeesService {

    private DataEmployeesRepository repository;

    private ModelMapper modelMapper;

    public EmployeesService(DataEmployeesRepository employeesRepository, ModelMapper modelMapper) {
        this.repository = employeesRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        var employee = repository.save(new Employee(command.getName()));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<EmployeeDto> listEmployees(String prefix) {

        log.info("Alkalmazottak listazasa");
        log.debug("A parameter: {}", prefix);

        return repository.findAll().stream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("emp not found")),
                EmployeeDto.class);
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee e = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("emp not found"));
        e.setName(command.getName());
        return modelMapper.map(e, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}