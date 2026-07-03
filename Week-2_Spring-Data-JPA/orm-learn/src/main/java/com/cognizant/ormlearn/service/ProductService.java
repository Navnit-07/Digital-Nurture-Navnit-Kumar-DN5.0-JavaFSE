package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/*
 * Hands-on 6: Criteria Query
 * Filters are added programmatically (like Amazon laptop filters).
 */
@Service
public class ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Product> searchProducts(String name,
                                        Double minReview,
                                        String ramSize,
                                        String operatingSystem,
                                        String cpu) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        // keyword search (like searching "laptop")
        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(cb.lower(product.get("name")), "%" + name.toLowerCase() + "%"));
        }

        // customer review filter
        if (minReview != null) {
            predicates.add(cb.greaterThanOrEqualTo(product.get("customerReview"), minReview));
        }

        // RAM size filter
        if (ramSize != null && !ramSize.isEmpty()) {
            predicates.add(cb.equal(product.get("ramSize"), ramSize));
        }

        // OS filter
        if (operatingSystem != null && !operatingSystem.isEmpty()) {
            predicates.add(cb.equal(product.get("operatingSystem"), operatingSystem));
        }

        // CPU filter
        if (cpu != null && !cpu.isEmpty()) {
            predicates.add(cb.equal(product.get("cpu"), cpu));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

    @Transactional
    public void save(Product product) {
        entityManager.persist(product);
    }
}
