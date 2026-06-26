package com.cognizant.ormlearn.hibernate;

import com.cognizant.ormlearn.model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/*
 * Hands-on 4: Hibernate approach
 * Uses Session, Transaction and manual session handling.
 * More boilerplate code compared to Spring Data JPA.
 */
public class HibernateEmployeeDao {

    private SessionFactory factory;

    public HibernateEmployeeDao(SessionFactory factory) {
        this.factory = factory;
    }

    // Method to CREATE an employee in the database
    public Integer addEmployee(Employee employee) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            tx = session.beginTransaction();
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }
}
