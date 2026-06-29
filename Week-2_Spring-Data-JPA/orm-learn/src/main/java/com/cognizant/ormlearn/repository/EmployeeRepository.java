package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Search by containing text
    List<Employee> findByNameContaining(String name);

    // Filter with starting text
    List<Employee> findByNameStartingWith(String prefix);

    // Sorting
    List<Employee> findByNameContainingOrderBySalaryDesc(String name);

    // Fetch between dates
    List<Employee> findByDateOfBirthBetween(Date startDate, Date endDate);

    // Greater than / lesser than
    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findBySalaryLessThan(double salary);

    // Top N
    List<Employee> findTop3ByOrderBySalaryDesc();
}
