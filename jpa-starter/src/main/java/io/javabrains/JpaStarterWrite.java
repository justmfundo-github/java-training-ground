package io.javabrains;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class JpaStarterWrite {
    public static void main(String[] args) {

        List<EmployeeDetails> employeeDetailsList = createEmployeeDetailsList();
        //System.out.println("****************LIST STARTS HERE**************" + employeeDetailsList);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        saveEmployees(entityManager, employeeDetailsList);

//        Employee updatableEmployee = entityManager.find(Employee.class, 1);
//        System.out.println("Printing employee from the database...\n" + updatableEmployee);
//        updatableEmployee.setAge(43);
//        updatableEmployee.setType(EmployeeType.FULL_TIME);
//        updateEmployee(entityManager, updatableEmployee);

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
            if(e.getPayStubList() != null){
               for(PayStub payStub : e.getPayStubList()){
                    System.out.println(payStub);
                    entityManager.persist(payStub);
               }
            }
            if(e.getEmailGroupList() != null){
                for(EmailGroup emailGroup : e.getEmailGroupList()){
                    System.out.println(emailGroup);
                    entityManager.persist(emailGroup);
                }
            }
        }
        transaction.commit();
    }

    public static List<EmployeeDetails> createEmployeeDetailsList(){

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

        // The below line isn't strictly necessary because the relationship between paystub and employee
        // is owned or managed on the paystub side. i.e payStub.setEmployee(payStub) already sorts out the
        // assignment.
        // But... JPA doesn't always update data WHEN called to do so. The instruction to update may happen later
        // So to ensure that the assignment has happened on the employee side by the time I access data from the
        // employee side in the code we perform cyclical relationships. I.e we do the assignment from the employee
        // side too. i.e. employee.employee1.setPayStubList(List.of(payStub1, payStub2));
        // JPA doesn't need the below line but it is recommended as a guarantee
        /*employee1.setPayStubList(List.of(payStub1, payStub2));*/// Alternatively add a convenience method to Employee
        // class to add paystub to the employee one by one as opposed to adding both to the list

        // Using the addPayStub convenience method from Employee class
        employee1.addPayStub(payStub1);
        employee1.addPayStub(payStub2);

        EmailGroup group1 = new EmailGroup();
        group1.setName("Company Watercooler Discussions");
        group1.addEmailGroupMember(employee1);
        group1.addEmailGroupMember(employee2);
        employee1.addEmailSubscription(group1);
        employee2.addEmailSubscription(group1);

        /* Remember the cyclical relationship. If you have ManyToMany relationships between two entities,
           it is best practice to set the values from both sides even though by setting the relationship
           on only one side might still work("usually").
         */

        EmailGroup group2 = new EmailGroup();
        group2.setName("Engineering Correspondence");
        employee1.addEmailSubscription(group2);
        group2.addEmailGroupMember(employee1);

        List<EmailGroup> emailGroupList = new ArrayList<EmailGroup>();
        emailGroupList.add(group1);
        emailGroupList.add(group2);

        //saveGenericEntityList(emailGroupList);

        List<PayStub> payStubList = new ArrayList<PayStub>();
        payStubList.add(payStub1);
        payStubList.add(payStub2);

        employeeDetails1.setEmployee(employee1);
        employeeDetails1.setAccessCard(card1);
        payStub1.setEmployee(employee1);
        payStub2.setEmployee(employee1);
        employeeDetails1.setPayStubList(payStubList);
        employeeDetails1.setEmailGroupList(emailGroupList);



        employeeDetails2.setEmployee(employee2);
        employeeDetails2.setAccessCard(card2);

        List<EmployeeDetails> employeeDetailsList = new ArrayList<EmployeeDetails>();
        employeeDetailsList.add(employeeDetails1);
        employeeDetailsList.add(employeeDetails2);

        return employeeDetailsList;
    }

}