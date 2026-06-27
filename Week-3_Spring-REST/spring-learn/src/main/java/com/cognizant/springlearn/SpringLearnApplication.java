package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * @SpringBootApplication combines:
 * - @Configuration
 * - @EnableAutoConfiguration
 * - @ComponentScan
 * It starts the Spring Boot application.
 */
@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Inside main");

        displayCountry();

        SpringApplication.run(SpringLearnApplication.class, args);
    }

    /*
     * Loads country bean from country.xml using ClassPathXmlApplicationContext.
     * When getBean() is called, Spring creates the bean (if not already created),
     * injects property values and returns the object.
     */
    private static void displayCountry() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        LOGGER.debug("Country : {}", country.toString());
    }
}
