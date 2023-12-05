package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaStarterRead {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        /*
        System.out.println("*************EMPLOYEE WAY***************");
        Employee employee = entityManager.find(Employee.class, 1);
        System.out.println("*********************** Fetched Employee");
        System.out.println(employee);
        System.out.println("*********************** Access Card");
        System.out.println(employee.getCard());

        int accessCardId = employee.getCard().getId();
        System.out.println("\\n\\n\\n\\");

        System.out.println("*************ACCESS CARD WAY***************");
        AccessCard card = entityManager.find(AccessCard.class, accessCardId);
        System.out.println("---------------------Fetched Access Card");
        System.out.println(card);
        System.out.println("---------------------Fetched Employee via Access Card");
        System.out.println(card.getOwner());
        */

        /*
        System.out.println("**************PRINTING PAYSTUBS*************");
        PayStub payStub = entityManager.find(PayStub.class, 3);

        System.out.println("*******AND THEN PRINT RELATED EMPLOYEE*******");
        System.out.println(payStub.getEmployee());
        */

        System.out.println("**************PAYSTUBS VIA EMPLOYEE*************");
        System.out.println("**************Before fetching the employee*************");
        Employee employee = entityManager.find(Employee.class, 1);
        System.out.println("**************Before fetching the paystubs*************");
        System.out.println(employee.getPayStubList());
    }

}
