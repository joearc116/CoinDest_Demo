package rest;

import com.cathaybk.dto.CurrencyDto;
import com.cathaybk.repository.CurrencyRepository;
import com.cathaybk.rest.CoinDeskController;
import com.cathaybk.service.CoinDeskService;
import com.cathaybk.service.CurrencyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = { })
@ContextConfiguration(classes = { CurrencyDto.class, CoinDeskService.class, CurrencyService.class,
        CoinDeskController.class })
@ComponentScan(basePackages = "com.cathaybk")
public class CoinDestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CoinDeskService coinDeskService;

    @Mock
    private CurrencyService currencyService;

    @MockBean
    private CurrencyRepository repository;

    @InjectMocks
    private CoinDeskController coinDeskController;

    @Mock
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(coinDeskController).build();
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGetOriginatedData() throws Exception {
        MockHttpServletResponse res =
                mockMvc.perform(get("/api/coindesk")).andReturn().getResponse();
    }

    @Test
    public void testGetTransformedData() throws Exception {
        MockHttpServletResponse res =
                mockMvc.perform(get("/api/coindesk/transformed")).andReturn().getResponse();
    }

}
