package io.javabrains;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class JpaStarterWrite {
    public static void main(String[] args) {

        List<EmployeeDetails> employeeDetailsList = createEmployeeAccessList();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        saveEmployees(entityManager, employeeDetailsList);

        Employee updatableEmployee = entityManager.find(Employee.class, 1);
        System.out.println("Printing employee from the database...\n" + updatableEmployee);
        updatableEmployee.setAge(43);
        updatableEmployee.setType(EmployeeType.FULL_TIME);
        updateEmployee(entityManager, updatableEmployee);

        entityManager.clear();
        entityManagerFactory.close();
    }
    private static void updateEmployee(EntityManager entityManager, Employee updatableEmployee) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(updatableEmployee);
        transaction.commit();
    }

    private static void saveEmployees(EntityManager entityManager, List<EmployeeDetails> employeeDetailsList) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        for(EmployeeDetails e : employeeDetailsList) {
            entityManager.persist(e.getEmployee());
            entityManager.persist(e.getAccessCard());
            for(PayStub payStub : e.getPayStubList()){
                entityManager.persist(payStub);
            }
        }
        transaction.commit();
    }

    public static List<EmployeeDetails> createEmployeeAccessList(){

        EmployeeDetails employeeDetails1 = new EmployeeDetails();
        EmployeeDetails employeeDetails2 = new EmployeeDetails();

        AccessCard card1 = new AccessCard();
        card1.setIssuedDate(new Date());
        card1.setActive(true);
        card1.setFirmwareVersion("1.0.0");


        AccessCard card2 = new AccessCard();
        card2.setIssuedDate(new Date());
        card2.setActive(false);
        card2.setFirmwareVersion("1.2.0");

        System.out.println(card1 + " ------AND----- " + card2);

        Employee employee1 = new Employee();
        //employee.setId(1);
        employee1.setSsn("4G49");
        employee1.setName("Foo Bar");
        employee1.setDob(new Date());
        employee1.setAge(20);
        employee1.setType(EmployeeType.FULL_TIME);
        employee1.setCard(card1);
        card1.setOwner(employee1);

        Employee employee2 = new Employee();
        //employee1.setId(2);
        employee2.setSsn("4H50");
        employee2.setName("Bar Baz");
        employee2.setDob(new Date());
        employee2.setAge(33);
        employee2.setType(EmployeeType.CONTRACTOR);
        employee2.setCard(card2);
        card2.setOwner(employee2);

        PayStub payStub1 = new PayStub();
        payStub1.setPayPeriodBegin(new Date());
        payStub1.setPayPeriodEnd(new Date());
        payStub1.setSalary(1000);

        PayStub payStub2 = new PayStub();
        payStub2.setPayPeriodBegin(new Date());
        payStub2.setPayPeriodEnd(new Date());
        payStub2.setSalary(1000);

        List<PayStub> payStubList = new ArrayList<PayStub>();
        payStubList.add(payStub1);
        payStubList.add(payStub2);


        payStub1.setEmployee(employee1);
        employeeDetails1.setEmployee(employee1);
        employeeDetails1.setAccessCard(card1);
        employeeDetails1.setPayStubList(payStubList);

        payStub2.setEmployee(employee2);
        employeeDetails2.setEmployee(employee2);
        employeeDetails2.setAccessCard(card2);

        List<EmployeeDetails> employeeDetailsList = new ArrayList<EmployeeDetails>();
        employeeDetailsList.add(employeeDetails1);
        employeeDetailsList.add(employeeDetails2);

        return employeeDetailsList;
    }
}