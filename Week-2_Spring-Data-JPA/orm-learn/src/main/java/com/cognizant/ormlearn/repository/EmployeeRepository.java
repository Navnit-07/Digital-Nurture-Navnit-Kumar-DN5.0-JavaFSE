package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // ----- Query Methods -----
    List<Employee> findByNameContaining(String name);

    List<Employee> findByNameStartingWith(String prefix);

    List<Employee> findByNameContainingOrderBySalaryDesc(String name);

    List<Employee> findByDateOfBirthBetween(Date startDate, Date endDate);

    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findBySalaryLessThan(double salary);

    List<Employee> findTop3ByOrderBySalaryDesc();

    // ----- JPQL / HQL using @Query -----
    // JPQL is almost same as HQL in Hibernate

    @Query("SELECT e FROM Employee e WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployees();

    // fetch keyword loads associations in same query (avoid lazy issues)
    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.department d LEFT JOIN FETCH e.skillList WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployeesWithFetch();

    // Aggregate function AVG
    @Query("SELECT AVG(e.salary) FROM Employee e")
    double getAverageSalary();

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id")
    double getAverageSalaryByDepartment(@Param("id") int id);

    // ----- Native Query (plain SQL) -----
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}
