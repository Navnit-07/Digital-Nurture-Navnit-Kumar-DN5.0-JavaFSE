package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
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

    public static void main(String[] args) throws ParseException {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        countryService = context.getBean(CountryService.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);

        testGetAllCountries();
        getAllCountriesTest();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testFindByPartialName();

        testGetEmployee();
        testAddEmployee();
    }

    private static void testGetAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End");
    }

    private static void getAllCountriesTest() {
        LOGGER.info("Start");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country:{}", country);
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
        try {
            LOGGER.debug("Added Country:{}", countryService.findCountryByCode("ZZ"));
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testUpdateCountry() {
        LOGGER.info("Start");
        try {
            countryService.updateCountry("ZZ", "Updated Test Country");
            LOGGER.debug("Updated Country:{}", countryService.findCountryByCode("ZZ"));
        } catch (CountryNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("End");
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("ZZ");
        LOGGER.debug("Deleted country with code ZZ");
        LOGGER.info("End");
    }

    private static void testFindByPartialName() {
        LOGGER.info("Start");
        LOGGER.debug("Matching countries:{}", countryService.findByPartialName("ou"));
        LOGGER.info("End");
    }

    private static void testGetEmployee() {
        LOGGER.info("=== Get Employee (ManyToOne, ManyToMany) Start ===");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.info("=== Get Employee End ===");
    }

    private static void testAddEmployee() throws ParseException {
        LOGGER.info("=== Add Employee Start ===");
        Employee employee = new Employee();
        employee.setName("NewEmp");
        employee.setSalary(40000);
        employee.setPermanent(true);
        employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1997-06-01"));

        Department department = departmentService.get(1);
        employee.setDepartment(department);

        Set<Skill> skillSet = new HashSet<>();
        skillSet.add(skillService.get(1));
        skillSet.add(skillService.get(2));
        employee.setSkillList(skillSet);

        employeeService.save(employee);
        LOGGER.debug("Saved employee:{}", employee);
        LOGGER.info("=== Add Employee End ===");
    }
}
