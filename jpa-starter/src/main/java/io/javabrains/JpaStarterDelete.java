package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaStarterDelete {
    public static void main(String[] args) {
        /* When deleting data, one must manage the relationships that the entity you are deleting had/has.
           I.e. In this case it might make sense to delete an employee along with the relationship it has
           with email group relationships.
           However, it might be best to not delete a payStub.
         */
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        //Employee employee = entityManager.find(Employee.class, 7);
        PayStub payStub = entityManager.find(PayStub.class, 3);
        transaction.begin();

        entityManager.remove(payStub);

        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
