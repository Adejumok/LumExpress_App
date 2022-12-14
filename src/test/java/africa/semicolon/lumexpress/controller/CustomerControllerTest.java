package africa.semicolon.lumexpress.controller;

import africa.semicolon.lumexpress.data.dto.request.CustomerRegistrationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
class CustomerControllerTest {


    private CustomerRegistrationRequest request;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        request = CustomerRegistrationRequest.builder()
                .email("test@gmail.com")
                .password("jdc")
                .country("Naija")
                .build();

    }

    @Test
    void registerControllerTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/customer")
                .contentType("application/json")
                .content(mapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andDo(MockMvcResultHandlers.print());

    }
}