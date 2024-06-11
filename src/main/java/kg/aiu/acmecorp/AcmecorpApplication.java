package kg.aiu.acmecorp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AcmecorpApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcmecorpApplication.class, args);
    }

}
