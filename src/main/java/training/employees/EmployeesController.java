package training.employees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    public EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

/*    @GetMapping
    public List<EmployeeDto> listEmployeesOld(Optional<String> prefix) {
        return employeesService.listEmployees(prefix.get());
    }
*/
    @GetMapping
    public List<EmployeeDto> listEmployees(@RequestParam(required = false) String prefix) {
        return employeesService.listEmployees(prefix);
    }

    @GetMapping("/{id}")
    public ResponseEntity findEmployeeById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(employeesService.findEmployeeById(id)); //employeesService.findEmployeeById(id);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@Valid @RequestBody CreateEmployeeCommand command) {
        return employeesService.createEmployee(command);
    }

    @PostMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") long id,
                                      @RequestBody UpdateEmployeeCommand command) {
        return employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") long id) {
//        try {
            employeesService.deleteEmployee(id);
            return ResponseEntity.ok().build();
//        } catch (IllegalArgumentException e){
//            return ResponseEntity.notFound().build();
//        }
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleNotFound() {
        System.out.println("Employee not found");
    }

}