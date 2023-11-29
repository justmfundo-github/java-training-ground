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
public class JpaStarterMain {
    public static void main(String[] args) {

        List<Employee> employeeList = createEmployeeList();


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        saveEmployees(entityManager, employeeList);

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

    private static void saveEmployees(EntityManager entityManager, List<Employee> employeeList) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        for(Employee e : employeeList) {
            entityManager.persist(e);
        }
        transaction.commit();
    }

    public static List<Employee> createEmployeeList(){
        Employee employee = new Employee();
        //employee.setId(1);
        employee.setSsn("4G49");
        employee.setName("Foo Bar");
        employee.setDob(new Date());
        employee.setAge(20);
        employee.setType(EmployeeType.FULL_TIME);

        Employee employee1 = new Employee();
        //employee1.setId(2);
        employee1.setSsn("4H50");
        employee1.setName("Bar Baz");
        employee1.setDob(new Date());
        employee1.setAge(33);
        employee1.setType(EmployeeType.CONTRACTOR);

        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(employee);
        employeeList.add(employee1);

        return  employeeList;
    }
}