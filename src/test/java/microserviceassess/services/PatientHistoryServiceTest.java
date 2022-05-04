package microserviceassess.services;

import microserviceassess.beans.Patient;
import microserviceassess.beans.PatientHistory;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PatientHistoryServiceTest {

    @Autowired
    private PatientHistoryService patientHistoryService;

    @Test
    public void assessmentGeneratorTest(){
        //GIVEN
        Patient patient = generateUser();
        List<PatientHistory> patientHistoryList = generatePatientHistoryList(patient);

        //WHEN
        String result = patientHistoryService.assessmentGenerator(patient, patientHistoryList);

        //THEN
        Assertions.assertEquals("Patient : Paul Atreides Test : TestIn Danger (age 22) diabetes assessment is: In Danger", result);
    }

    @Test
    public void givenAPatientAndAPatientHistoryList_ReturnAStringOfDangerLevel(){
        //GIVEN
        Patient patient = generateUser();
        List<PatientHistory> patientHistoryList = generatePatientHistoryList(patient);

        //WHEN
        String dangerLevel = patientHistoryService.findDangerLevel(patient, patientHistoryList);

        //THEN
        Assertions.assertEquals("In Danger", dangerLevel);
    }

    private Patient generateUser(){
        Patient patient = new Patient();
        patient.setPatientId(100);
        patient.setFirstName("Paul");
        patient.setLastName("Atreides");
        LocalDate date = LocalDate.parse("2000-11-26");
        patient.setBirthDate(java.sql.Date.valueOf(date));
        patient.setGender("M");
        patient.setAddress("Planet Atreides");
        patient.setPhone("0648656565");
        patient.setDangerLevel("None");

        return patient;
    }

    private List<PatientHistory> generatePatientHistoryList(Patient patient){
        PatientHistory patientHistory1 = new PatientHistory();
        patientHistory1.setPatientHistoryId(new ObjectId());
        patientHistory1.setPatientId(patient.getPatientId());
        patientHistory1.setNotes("The test give the following results : Cholestérol, Vertige");

        PatientHistory patientHistory2 = new PatientHistory();
        patientHistory2.setPatientHistoryId(new ObjectId());
        patientHistory2.setPatientId(patient.getPatientId());
        patientHistory2.setNotes("Lorem Ipsum anticorps");

        List<PatientHistory> patientHistoryList = new ArrayList<>();
        patientHistoryList.add(patientHistory1);
        patientHistoryList.add(patientHistory2);

        return patientHistoryList;
    }
}
