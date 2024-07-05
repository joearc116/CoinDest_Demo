package rest;

import com.cathaybk.dto.CurrencyDto;
import com.cathaybk.repository.CurrencyRepository;
import com.cathaybk.rest.CurrencyController;
import com.cathaybk.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = { CurrencyDto.class, CurrencyService.class,
        CurrencyController.class, CurrencyRepository.class })
@ComponentScan(basePackages = "com.cathaybk")
public class CurrencyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CurrencyService currencyService;

    @MockBean
    private CurrencyRepository repository;

    @InjectMocks
    private CurrencyController currencyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(currencyController).build();
    }

    @Test
    public void testAddCurrency() throws Exception {
        mockMvc.perform(post("/api/currency").contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"USD\", \"name\": \"美金\"}"));
    }

    @Test
    public void testGetAllCurrencies() throws Exception {
        MockHttpServletResponse res = mockMvc.perform(get("/api/currency")).andReturn().getResponse();
    }


    @Test
    public void testUpdateCurrency() throws Exception {
        mockMvc.perform(put("/api/currency").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\": \"USD\", \"name\": \"美金\"}"));
    }

    @Test
    public void testDeleteCurrency() throws Exception {
        mockMvc.perform(delete("/api/currency/1"))
                .andExpect(status().isOk());
    }
}
