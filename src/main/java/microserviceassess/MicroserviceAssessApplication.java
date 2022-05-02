package microserviceassess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("microserviceassess")
public class MicroserviceAssessApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceAssessApplication.class, args);
    }
}
