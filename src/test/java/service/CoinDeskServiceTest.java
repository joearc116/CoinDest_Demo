package service;

import com.cathaybk.repository.CurrencyRepository;
import com.cathaybk.service.CoinDeskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = { CoinDeskService.class, CurrencyRepository.class })
@ComponentScan(basePackages = "com.cathaybk")
public class CoinDeskServiceTest {

    @InjectMocks
    private CoinDeskService coinDeskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCoinDeskData() throws Exception {
        // Invoke the method to test
        coinDeskService.getCoinDeskData();
    }
}
