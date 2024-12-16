package dev.manyroads;

import dev.manyroads.steps.decom.StartDecomStep;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RunSpring {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RunSpring.class);
    }
}
