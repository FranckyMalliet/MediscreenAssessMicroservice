package microserviceassess.controllers;

import microserviceassess.proxies.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientController {

    private PatientProxy patientProxy;

    public PatientController(PatientProxy patientProxy){
        this.patientProxy = patientProxy;
    }

    @GetMapping("/assess/list")
    public String findPatientList(Model model){
        model.addAttribute("patients", patientProxy.findAll());
        return "assess/list";
    }
}
