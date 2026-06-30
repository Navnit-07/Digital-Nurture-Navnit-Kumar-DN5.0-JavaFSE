package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public Employee get(int id) {
        return employeeRepository.findById(id).get();
    }

    @Transactional
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    public List<Employee> findByNameContaining(String name) {
        return employeeRepository.findByNameContaining(name);
    }

    @Transactional
    public List<Employee> findByNameStartingWith(String prefix) {
        return employeeRepository.findByNameStartingWith(prefix);
    }

    @Transactional
    public List<Employee> findByNameContainingSortedBySalary(String name) {
        return employeeRepository.findByNameContainingOrderBySalaryDesc(name);
    }

    @Transactional
    public List<Employee> findByDateOfBirthBetween(Date start, Date end) {
        return employeeRepository.findByDateOfBirthBetween(start, end);
    }

    @Transactional
    public List<Employee> findBySalaryGreaterThan(double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    @Transactional
    public List<Employee> findBySalaryLessThan(double salary) {
        return employeeRepository.findBySalaryLessThan(salary);
    }

    @Transactional
    public List<Employee> findTop3BySalary() {
        return employeeRepository.findTop3ByOrderBySalaryDesc();
    }

    @Transactional
    public List<Employee> getAllPermanentEmployees() {
        return employeeRepository.getAllPermanentEmployees();
    }

    @Transactional
    public List<Employee> getAllPermanentEmployeesWithFetch() {
        return employeeRepository.getAllPermanentEmployeesWithFetch();
    }

    @Transactional
    public double getAverageSalary() {
        return employeeRepository.getAverageSalary();
    }

    @Transactional
    public double getAverageSalaryByDepartment(int id) {
        return employeeRepository.getAverageSalaryByDepartment(id);
    }

    @Transactional
    public List<Employee> getAllEmployeesNative() {
        return employeeRepository.getAllEmployeesNative();
    }
}
