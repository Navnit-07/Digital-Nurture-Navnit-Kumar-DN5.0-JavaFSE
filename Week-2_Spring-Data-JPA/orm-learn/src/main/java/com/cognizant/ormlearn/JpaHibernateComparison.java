package com.cognizant.ormlearn;

/*
 * Hands-on 4: Difference between JPA, Hibernate and Spring Data JPA
 *
 * JPA (Java Persistence API):
 * - JSR 338 specification for persisting data from Java objects
 * - Only a specification, no concrete implementation
 *
 * Hibernate:
 * - ORM tool that implements JPA
 * - We write Session, Transaction, commit, rollback manually
 * - See HibernateEmployeeDao.java
 *
 * Spring Data JPA:
 * - Does not implement JPA itself
 * - Adds abstraction over JPA provider (like Hibernate)
 * - Reduces boilerplate code and manages transactions
 * - See EmployeeRepository.java and EmployeeService.java
 */
public class JpaHibernateComparison {

    public static void main(String[] args) {

        System.out.println("=== JPA vs Hibernate vs Spring Data JPA ===\n");

        System.out.println("JPA:");
        System.out.println("- Specification for persisting Java objects");
        System.out.println("- No implementation, only rules/API\n");

        System.out.println("Hibernate:");
        System.out.println("- Implements JPA");
        System.out.println("- Need Session, Transaction, try-catch, rollback");
        System.out.println("- More code to write\n");

        System.out.println("Spring Data JPA:");
        System.out.println("- Abstraction over JPA (uses Hibernate behind the scenes)");
        System.out.println("- Just extend JpaRepository and call save()");
        System.out.println("- @Transactional handles transaction automatically");
    }
}
