package microserviceassess.controllers;

import microserviceassess.beans.Patient;
import microserviceassess.beans.PatientHistory;
import microserviceassess.proxies.PatientHistoryProxy;
import microserviceassess.proxies.PatientProxy;
import microserviceassess.services.PatientHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PatientHistoryController {

    private final static Logger logger = LoggerFactory.getLogger(PatientHistoryController.class);
    private PatientProxy patientProxy;
    private PatientHistoryProxy patientHistoryProxy;
    private PatientHistoryService patientHistoryService;

    public PatientHistoryController(PatientProxy patientProxy, PatientHistoryProxy patientHistoryProxy, PatientHistoryService patientHistoryService){
        this.patientProxy = patientProxy;
        this.patientHistoryProxy = patientHistoryProxy;
        this.patientHistoryService = patientHistoryService;
    }

    @GetMapping("/assess/generateById/{id}")
    public String createAssessUsingPatientId(@PathVariable("id") int patientId, Model model){
        Patient patient = patientProxy.findById(patientId);
        List<PatientHistory> patientHistoryList = patientHistoryProxy.findPatientHistoryListById(patientId);

        String result = patientHistoryService.assessmentGenerator(patient, patientHistoryList);
        logger.info(result);

        patient.setDangerLevel(patientHistoryService.findDangerLevel(patient, patientHistoryList));
        patientProxy.updatePatientDangerLevel(patient);

        logger.debug("Generating assess using patient ID");
        model.addAttribute("patients", patientProxy.findAll());
        return "redirect:/assess/list";
    }

    @GetMapping("/assess/generateByLastName/{lastName}")
    public String createAssessUsingLastName(@PathVariable("lastName") String lastName, Model model){
        Patient patient =patientProxy.findByLastName(lastName);
        List<PatientHistory> patientHistoryList = patientHistoryProxy.findPatientHistoryListById(patient.getPatientId());

        String result = patientHistoryService.assessmentGenerator(patient, patientHistoryList);
        logger.info(result);

        patient.setDangerLevel(patientHistoryService.findDangerLevel(patient, patientHistoryList));
        patientProxy.updatePatientDangerLevel(patient);

        logger.debug("Generating assess using last name");
        model.addAttribute("patients", patientProxy.findAll());
        return "redirect:/assess/list";
    }
}
