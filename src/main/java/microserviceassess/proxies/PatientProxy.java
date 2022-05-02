package microserviceassess.proxies;

import microserviceassess.beans.Patient;
import microserviceassess.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="patient", url="${url.patient:http://patient}", configuration = FeignConfig.class)
public interface PatientProxy {

    @GetMapping("/patient/assess/list")
    List<Patient> findAll();

    @GetMapping("patient/assess/patientId")
    Patient findById(@RequestParam("id") Integer patientId);

    @GetMapping("patient/assess/patientLastName")
    Patient findByLastName(@RequestParam("lastName") String lastName);
}
