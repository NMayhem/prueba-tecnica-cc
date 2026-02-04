package com.aidaml.cc.demo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aidaml.cc.demo.model.domain.User;
import com.aidaml.cc.demo.model.dto.OrderBy;

@Repository
public class UserCriteriaRepository {
    
    @Autowired
    private EntityManager em;

    public List<User> findFiltered(String filter, OrderBy orderBy) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        String[] filterParts = filter.split("\\+");

        if(filterParts.length == 3) {
            String attribute = filterParts[0];
            String operator = filterParts[1];
            String value = filterParts[2];
    
            Predicate filterPredicate = switch (operator) {
                case "co" -> criteriaBuilder.like(userRoot.get(attribute), "%" + value + "%");
                case "eq" -> criteriaBuilder.equal(userRoot.get(attribute), value);
                case "sw" -> criteriaBuilder.like(userRoot.get(attribute), value + "%");
                case "ew" -> criteriaBuilder.like(userRoot.get(attribute), "%" + value);
                default -> criteriaBuilder.disjunction();
            };

            predicates.add(filterPredicate);

        } else {
            predicates.add(criteriaBuilder.disjunction());
        }
        
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        if (orderBy != null) {
            criteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get(orderBy.toString())));
        }

        return em.createQuery(criteriaQuery).getResultList();
    }

}
