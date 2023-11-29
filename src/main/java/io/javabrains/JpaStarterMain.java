package io.javabrains;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class JpaStarterMain {
    public static void main(String[] args) {

        List<EmployeeAccess> employeeAccessList = createEmployeeAccessList();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        saveEmployees(entityManager, employeeAccessList);

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

    private static void saveEmployees(EntityManager entityManager, List<EmployeeAccess> employeeAccessList) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        for(EmployeeAccess e : employeeAccessList) {
            entityManager.persist(e.getEmployee());
            entityManager.persist(e.getAccessCard());
        }
        transaction.commit();
    }

    public static List<EmployeeAccess> createEmployeeAccessList(){

        EmployeeAccess employeeAccess1 = new EmployeeAccess();
        EmployeeAccess employeeAccess2 = new EmployeeAccess();

        AccessCard card1 = new AccessCard();
        card1.setIssuedDate(new Date());
        card1.setActive(true);
        card1.setFirmwareVersion("1.0.0");

        AccessCard card2 = new AccessCard();
        card2.setIssuedDate(new Date());
        card2.setActive(false);
        card2.setFirmwareVersion("1.2.0");

        System.out.println(card1 + " ------AND----- " + card2);

        Employee employee = new Employee();
        //employee.setId(1);
        employee.setSsn("4G49");
        employee.setName("Foo Bar");
        employee.setDob(new Date());
        employee.setAge(20);
        employee.setType(EmployeeType.FULL_TIME);
        employee.setCard(card1);

        Employee employee1 = new Employee();
        //employee1.setId(2);
        employee1.setSsn("4H50");
        employee1.setName("Bar Baz");
        employee1.setDob(new Date());
        employee1.setAge(33);
        employee1.setType(EmployeeType.CONTRACTOR);
        employee1.setCard(card2);

        employeeAccess1.setEmployee(employee);
        employeeAccess1.setAccessCard(card1);

        employeeAccess2.setEmployee(employee1);
        employeeAccess2.setAccessCard(card2);

        List<EmployeeAccess> employeeAccessListList = new ArrayList<EmployeeAccess>();
        employeeAccessListList.add(employeeAccess1);
        employeeAccessListList.add(employeeAccess2);

        return  employeeAccessListList;
    }
}