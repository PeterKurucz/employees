package training.employees;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @Test
    void sayHello() {
        Assertions.assertThat(new HelloService().sayHello().startsWith("Hello Spring Boot"));
    }
}
