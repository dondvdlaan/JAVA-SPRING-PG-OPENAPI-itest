package dev.manyroads.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = SpringTestConfig.class)
public class CucumberSpringConfiguration { }
