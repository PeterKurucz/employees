package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EmployeesControllerIT {

    @MockBean
    EmployeesService employeesService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testCreate() throws Exception {
        when(employeesService.createEmployee(any())).thenReturn(new EmployeeDto(10, "kurucz"));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"John Doe\"}"))
                .andExpect(status().isCreated())
        .andDo(r -> System.out.println(r.getResponse().getContentAsString()))
        .andExpect(jsonPath("$.name", equalTo("kurucz")));

        verify(employeesService).createEmployee(argThat(e -> e.getName().equals("John Doe")));
        verify(employeesService, never()).listEmployees(anyString());
    }

}
