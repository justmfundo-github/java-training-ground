package io.javabrains;

import javax.persistence.*;
import java.util.List;

public class JpaJPQLExample3 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Be careful about what result you're receiving... You need to specify the type of result you expect
        // I.e. select e.name from Employee e will return a String.class as opposed to an Employee.class
        TypedQuery<Employee> typedQuery = entityManager.createQuery(
                "select e from Employee e", Employee.class
        );
        List<Employee> resultEmployeeList = typedQuery.getResultList();
        resultEmployeeList.forEach(e -> System.out.println(e.getName() + " " + e.getAge()));

        // select e.name, e.age from Employee e cannot be typed so do not specify a type which also means that you
        // cannot create a Typed query
        Query query = entityManager.createQuery("select e.name, e.age from Employee e");
        // To get the values, you need to get a generic list from the result set
        List resultList = query.getResultList();
        // Thus the result set returned will simply be an array of Objects
        // Print out the list
        resultList.forEach(System.out::println);

        entityManager.close();
        entityManagerFactory.close();
    }
}
