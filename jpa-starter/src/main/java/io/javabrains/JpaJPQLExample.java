package io.javabrains;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaJPQLExample {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        /* Creating a query using jpql
        *  Note differences between sql and jpql:
        *  SQL: select * from employee_data // Note the use of "e" instead "*" also we select from Employee
        *  class instead of employee_data table
        *  JPQL: select e from Employee e  // Instead of "*" we use an alias
        * */

        // Use TypedQuery to retrieve the data as employee class
        TypedQuery<Employee> typedQuery = entityManager.createQuery("select e from Employee e", Employee.class);

        // Use the generic Query to retrieve generic Object
        // Query query = entityManager.createQuery("select e from Employee e where e.age > 25");

        // Order by
        // Query query = entityManager.createQuery("select e from Employee e order by e.name");

        // Like
        // Query query = entityManager.createQuery("select e from Employee e where e.name like '%Bar'");

        // Between
        // Query query = entityManager.createQuery("select e from Employee e where e.age between 22 and 32");

        // Join
        // Because the Employee entity already has an implicit join using @OneToOne annotation, there is no need to
        // perform an actual join in the query
        //Query query = entityManager.createQuery("select e from Employee e where e.card.isActive = true");

        // Explicit Join
        Query query = entityManager.createQuery("select e from Employee e join AccessCard a on e.card = a.id");

        List resultList = query.getResultList();

        resultList.forEach(System.out::println);

        entityManager.close();
        entityManagerFactory.close();
    }
}
