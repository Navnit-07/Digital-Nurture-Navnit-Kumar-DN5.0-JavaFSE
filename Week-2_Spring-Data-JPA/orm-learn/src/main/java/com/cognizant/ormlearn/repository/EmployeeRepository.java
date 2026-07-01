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

    /*
     * Hands-on 2: HQL with fetch
     * Join links tables; fetch populates the beans (department + skills)
     * in a single SQL query.
     */
    @Query(value = "SELECT e FROM Employee e left join fetch e.department d left join fetch e.skillList WHERE e.permanent = 1")
    List<Employee> getAllPermanentEmployees();

    // Hands-on 4: average salary (all employees)
    @Query(value = "SELECT AVG(e.salary) FROM Employee e")
    double getAverageSalary();

    // Hands-on 4: average salary filtered by department id
    @Query(value = "SELECT AVG(e.salary) FROM Employee e where e.department.id = :id")
    double getAverageSalary(@Param("id") int id);

    // Hands-on 5: Native Query (plain SQL)
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}
