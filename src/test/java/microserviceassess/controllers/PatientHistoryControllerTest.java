package microserviceassess.controllers;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@SpringBootTest
@AutoConfigureWireMock(port = 100)
public class PatientHistoryControllerTest {

    @Test
    public void givenAPatientId_GenerateAStringAssessment_ReturnAPageWithAllPatients() throws Exception {
        //WHEN
        stubFor(WireMock.get(urlEqualTo("/assess/generateById/{id}"))
                .withQueryParam("id", equalTo(String.valueOf(1)))
                .willReturn(aResponse().withStatus(200)));

        //THEN
        stubFor(WireMock.get(urlEqualTo("/assess/list"))
                .willReturn(aResponse().withStatus(200)));
    }

    @Test
    public void givenAPatientLastName_GenerateAStringAssessment_ReturnAPageWithAllPatients() throws Exception {
        //WHEN
        stubFor(WireMock.get(urlEqualTo("/assess/generateById/{lastName}"))
                .withQueryParam("lastName", equalTo("Valante"))
                .willReturn(aResponse().withStatus(200)));

        //THEN
        stubFor(WireMock.get(urlEqualTo("/assess/list"))
                .willReturn(aResponse().withStatus(200)));
    }
}
