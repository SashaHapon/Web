package org.food.dao;

import org.food.model.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;






public class CriteriaApiRepository {


    EntityManager entityManager;

    public void createCriteriaApiQuery() {

        Account account = new Account();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> query = criteriaBuilder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);

        Predicate namePredicate = criteriaBuilder.equal(root.get("name"), "Jack");
        Predicate idPredicate = criteriaBuilder.equal(root.get("id"), "bced60ce-7066-49f7-ad59-29d670299098");

        Predicate andPredicate = criteriaBuilder.and(namePredicate, idPredicate);

        query.where(andPredicate);

        Account accounts = entityManager.createQuery(query).getSingleResult();
    }


    public void createJpqlQuery() {
        TypedQuery<Account> query = entityManager.createQuery(
                "SELECT u FROM Account u WHERE u.name = 'Jack'", Account.class);
        query.setParameter("id", "bced60ce-7066-49f7-ad59-29d670299098");
        Account account = query.getSingleResult();
    }
}
