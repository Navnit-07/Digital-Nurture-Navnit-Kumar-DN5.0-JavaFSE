package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.*;
import com.cognizant.ormlearn.service.*;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;
    private static AttemptService attemptService;

    public static void main(String[] args) throws ParseException {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        countryService = context.getBean(CountryService.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);
        attemptService = context.getBean(AttemptService.class);

        testGetAllCountries();
        getAllCountriesTest();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testFindByPartialName();
        testGetEmployee();
        testAddEmployee();
        testQueryMethods();
        testGetAllPermanentEmployees();
        testAverageSalary();
        testNativeQuery();
        testGetAttempt();
    }

    public static void testGetAllPermanentEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("End");
    }

    private static void testAverageSalary() {
        LOGGER.info("Start");
        LOGGER.debug("Average Salary:{}", employeeService.getAverageSalary());
        LOGGER.debug("Average Salary for Dept 2:{}", employeeService.getAverageSalary(2));
        LOGGER.info("End");
    }

    private static void testNativeQuery() {
        LOGGER.info("Start");
        LOGGER.debug("Native Employees:{}", employeeService.getAllEmployeesNative());
        LOGGER.info("End");
    }

    private static void testGetAttempt() {
        LOGGER.info("Start");
        Attempt attempt = attemptService.getAttempt(1, 1);
        if (attempt == null) {
            LOGGER.error("Attempt not found. Run sql/quiz.sql first.");
            LOGGER.info("End");
            return;
        }
        LOGGER.debug("User:{}", attempt.getUser().getName());
        LOGGER.debug("Attempt Date:{}", attempt.getDate());
        for (AttemptQuestion aq : attempt.getAttemptQuestionList()) {
            System.out.println(aq.getQuestion().getText());
            int optNo = 1;
            for (AttemptOption ao : aq.getAttemptOptionList()) {
                Options op = ao.getOptions();
                System.out.println(" " + optNo + ") " + op.getText()
                        + "\t" + op.getScore() + "\t" + ao.isSelected());
                optNo++;
            }
            System.out.println();
        }
        LOGGER.info("End");
    }

    private static void testQueryMethods() throws ParseException {
        LOGGER.info("Start");
        LOGGER.debug("Top 3 by salary: {}", employeeService.findTop3BySalary());
        LOGGER.info("End");
    }

    private static void testGetAllCountries() {
        LOGGER.info("Start");
        LOGGER.debug("countries={}", countryService.getAllCountries());
        LOGGER.info("End");
    }

    private static void getAllCountriesTest() {
        LOGGER.info("Start");
        try {
            LOGGER.debug("Country:{}", countryService.findCountryByCode("IN"));
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testAddCountry() {
        LOGGER.info("Start");
        Country country = new Country();
        country.setCode("ZZ");
        country.setName("Test Country");
        countryService.addCountry(country);
        LOGGER.info("End");
    }

    private static void testUpdateCountry() {
        LOGGER.info("Start");
        try {
            countryService.updateCountry("ZZ", "Updated");
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("ZZ");
        LOGGER.info("End");
    }

    private static void testFindByPartialName() {
        LOGGER.info("Start");
        LOGGER.debug("{}", countryService.findByPartialName("ou"));
        LOGGER.info("End");
    }

    private static void testGetEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.info("End");
    }

    private static void testAddEmployee() throws ParseException {
        LOGGER.info("Start");
        Employee employee = new Employee();
        employee.setName("NewEmp");
        employee.setSalary(40000);
        employee.setPermanent(true);
        employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1997-06-01"));
        employee.setDepartment(departmentService.get(1));
        Set<Skill> skills = new HashSet<>();
        skills.add(skillService.get(1));
        employee.setSkillList(skills);
        employeeService.save(employee);
        LOGGER.info("End");
    }
}
