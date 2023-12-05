package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStarterUpdate {
    public static void main(String[] args) {

        /* To update existing data, we pull the data from the database as an entity,
           make changes to the entity and then persist that entity
         */
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Employee employee = entityManager.find(Employee.class, 7);
        EmailGroup emailGroup = entityManager.find(EmailGroup.class, 6);

        employee.addEmailSubscription(emailGroup);
        emailGroup.addEmailGroupMember(employee);

        transaction.begin();

        entityManager.persist(emailGroup);
        entityManager.persist(employee);

        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();

    }
}
