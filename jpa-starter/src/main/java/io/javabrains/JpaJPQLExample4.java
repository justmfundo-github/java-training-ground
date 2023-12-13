package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaJPQLExample4 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // When you query an entity but instead of returning the entity class, you want specific parts like
        // name and age from the Employee class. The way you handle the result set has to be different because, in thi
        // case the returned data will consist of a List of array of objects.

        TypedQuery<Object[]> query = entityManager.createQuery(
                //"select e.name, e.age, e.dob, c.issuedDate from Employee e, AccessCard c where e.card.id = c.id",
                //The above where clause isn't necessary because the relationship is implicit
                "select e.name, e.age, e.dob, e.card.issuedDate from Employee e",
                Object[].class
        );

        List<Object[]> resultList = query.getResultList();
        resultList.forEach(e -> System.out.println(e[0] + " " + e[1] + " " + e[2] + " " + e[3]));

        entityManager.close();
        entityManagerFactory.close();
    }
}
