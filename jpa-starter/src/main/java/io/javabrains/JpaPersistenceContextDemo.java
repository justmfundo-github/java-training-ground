package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class JpaPersistenceContextDemo {
    public static void main(String[] args) {
        AccessCard card1 = new AccessCard();
        card1.setIssuedDate(new Date());
        card1.setActive(true);
        card1.setFirmwareVersion("1.0.0");

        Employee employee1 = new Employee();
        //employee.setId(1);
        employee1.setSsn("4G49");
        employee1.setName("Foo Bar");
        employee1.setDob(new Date());
        employee1.setAge(20);
        employee1.setType(EmployeeType.FULL_TIME);
        //employee1.setCard(card1);
        //card1.setOwner(employee1);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        System.out.println("********************STARTING TRANSACTION********************");
        entityManager.persist(employee1); // Key take away here is that the insert doesn't actually happen immediately. Hibernate manages
        // or chooses when to run the insert
        System.out.println("********************AFTER PERSIST METHOD IS CALLED**********");

        // Let's see if we can find the employee before the commit occurs/i.e. before the insert happens
        Employee employeeFound = entityManager.find(Employee.class, 1);
        // Employee abover is found even though the insert into table hasn't yet occured. In short the entityManager keeps a cache of the data
        System.out.println(employeeFound);
        System.out.println("employee1 == to foundEmployee is: " + (employee1 == employeeFound));

        transaction.commit();
        System.out.println("********************AFTER TRANSACTION IS CLOSED*************");

        entityManager.clear();
        entityManagerFactory.close();
    }
}