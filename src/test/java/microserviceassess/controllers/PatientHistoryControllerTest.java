package microserviceassess.controllers;

import microserviceassess.beans.Patient;
import microserviceassess.beans.PatientHistory;
import microserviceassess.proxies.PatientHistoryProxy;
import microserviceassess.proxies.PatientProxy;
import microserviceassess.services.PatientHistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PatientHistoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientProxy patientProxy;

    @Autowired
    private PatientHistoryProxy patientHistoryProxy;

    @Autowired
    private PatientHistoryService patientHistoryService;

    @BeforeEach
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void givenAPatientId_GenerateAStringAssessment_ReturnAPageWithAllPatients() throws Exception {
        //GIVEN
        Patient patient = patientProxy.findById(1);
        Assertions.assertNotNull(patient);

        List<PatientHistory> patientHistoryList = patientHistoryProxy.findPatientHistoryListById(patient.getPatientId());
        Assertions.assertNotNull(patientHistoryList);

        //WHEN
        String result = patientHistoryService.assessmentGenerator(patient, patientHistoryList);
        Assertions.assertNotNull(result);

        //THEN
        mockMvc.perform(get("/assess/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenAPatientLastName_GenerateAStringAssessment_ReturnAPageWithAllPatients() throws Exception {
        //GIVEN
        Patient patient = patientProxy.findByLastName("");
        Assertions.assertNotNull(patient);

        List<PatientHistory> patientHistoryList = patientHistoryProxy.findPatientHistoryListById(patient.getPatientId());
        Assertions.assertNotNull(patientHistoryList);

        //WHEN
        String result = patientHistoryService.assessmentGenerator(patient, patientHistoryList);
        Assertions.assertNotNull(result);

        //THEN
        mockMvc.perform(get("/assess/list"))
                .andExpect(status().isOk());
    }
}
