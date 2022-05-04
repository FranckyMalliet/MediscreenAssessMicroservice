package microserviceassess.controllers;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@SpringBootTest
@AutoConfigureWireMock(port = 100)
public class PatientControllerTest {

    @Test
    public void givenAnUrl_ReturnAPageWithAllPatients() throws Exception {
        stubFor(get(urlEqualTo("/assess/list"))
                .willReturn(aResponse().withStatus(200)));
    }
}
