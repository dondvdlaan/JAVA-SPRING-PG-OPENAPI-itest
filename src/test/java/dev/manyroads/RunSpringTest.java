package dev.manyroads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RunSpringTest {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RunSpringTest.class);
    }
}
