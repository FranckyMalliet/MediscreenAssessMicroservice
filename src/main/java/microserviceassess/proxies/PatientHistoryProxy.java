package microserviceassess.proxies;

import microserviceassess.beans.PatientHistory;
import microserviceassess.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="patienthistory", url="${url.patienthistory:http://patienthistory}", configuration = FeignConfig.class)
public interface PatientHistoryProxy {

    @GetMapping("/patientHistory/assess/patientHistoryId")
    List<PatientHistory> findPatientHistoryListById(@RequestParam("patientId") int patientId);
}
