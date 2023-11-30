package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaStarterRead {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("*************EMPLOYEE WAY***************");
        Employee employee = entityManager.find(Employee.class, 1);
        System.out.println("*********************** Fetched Employee");
        System.out.println(employee);
        System.out.println("*********************** Access Card");
        System.out.println(employee.getCard());

        System.out.println("\\n\\n\\n\\");

        System.out.println("*************ACCESS CARD WAY***************");
        AccessCard card = entityManager.find(AccessCard.class, 4);
        System.out.println("---------------------Fetched Access Card");
        System.out.println(card);
        System.out.println("---------------------Fetched Employee via Access Card");
        System.out.println(card.getOwner());
    }

}
