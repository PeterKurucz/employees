package training.employees;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {

    public Long id;
    public String name;

    public Employee(String name) {
        this.name = name;
    }
}
