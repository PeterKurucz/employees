package training.employees;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HelloControllerTest {

    @Mock
    HelloService helloService;

    @InjectMocks
    HelloController helloController;

    @Test
    void sayHello() {
        when(helloService.sayHello()).thenReturn("hello world");
        Assertions.assertThat(helloController.sayHello()).isEqualTo("HELLO WORLD");
    }
}