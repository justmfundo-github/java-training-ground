package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaJPQLExample2 {
    public static void main(String[] args) {
        /* Continuing to play with JPQL queries though moving away from known result types like a full Entity
           I.e. what if we want just the Age or Name of an employee
         */

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<String> typedQuery = entityManager.createQuery(
                "select e.name from Employee e", String.class
        );

        List<String> resultList = typedQuery.getResultList();
        resultList.forEach(System.out::println);

        /* What if we want arbitrary values like name and age and date of birth. Then we're effectively retrieving
           an array of objects
         */
//        TypedQuery<Object[]> typeObjectsQuery = entityManager.createQuery(
//                "select e.name, e.age, e.dob from Employee e", Object[].class
//        );

        /* Now throw in retrieving other Entities linked to the Employee

         */
        TypedQuery<Object[]> typeObjectsQuery = entityManager.createQuery(
                "select e.name, e.card.issuedDate from Employee e", Object[].class
        );

        List<Object[]> resultObjectsList = typeObjectsQuery.getResultList();
        //resultObjectsList.forEach(e -> System.out.println(e[0] + " " + e[1] + " " + e[2]));
        resultObjectsList.forEach(e -> System.out.println(e[0] + " " + e[1]));

        entityManager.close();
        entityManagerFactory.close();
    }
}
