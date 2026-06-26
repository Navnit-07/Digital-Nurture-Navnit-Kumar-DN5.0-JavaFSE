package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Hands-on 4: Spring Data JPA approach
 * No need to write save logic - JpaRepository provides it.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
