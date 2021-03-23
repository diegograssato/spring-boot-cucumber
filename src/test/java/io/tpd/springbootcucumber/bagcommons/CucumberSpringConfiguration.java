package io.tpd.springbootcucumber.bagcommons;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class CucumberSpringConfiguration {

    /**
     * Need this method so the cucumber will recognize this class as glue and load spring context configuration.
     */
    @Before
    public void setUp() {

        log.info("=Spring Context initialized for executing cucumber tests.");
    }
}
