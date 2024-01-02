package io.javabrains;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JpaJPQLExample5 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Simply using a String variable in a query has it's drawbacks. Opens you to SQL injection
        // I.e String name = "Bar Baz; delete * from EMPLOYEE_TABLE";
        String empName = "Bar Baz";
        TypedQuery<Employee> query = entityManager.createQuery(
                "select e from Employee e where e.name = :empParameterName", // Note the use of parameter
                Employee.class
        );
        // Parameter use is JPA's way of making the query safe from SQL injection...
        // JPA expects a parameter, not another query
        query.setParameter("empParameterName", empName); // assigning parameter to the value empName
        List<Employee> resultList = query.getResultList();
        resultList.forEach(System.out::println);

        entityManager.close();
        entityManagerFactory.close();
    }
}
