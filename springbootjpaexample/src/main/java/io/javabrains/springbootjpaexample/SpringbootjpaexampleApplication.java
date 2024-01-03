package io.javabrains.springbootjpaexample;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringbootjpaexampleApplication {
	@PersistenceUnit
	private EntityManagerFactory emf;

	// NB: It's possible in spring boot to avoid having to create the abover EntityManagerFactory
	// You can get the EntityManager and annotate it as Persistence Context directly as follows
	/*
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	*/
	// BUT there is a GOTCHA. The entityManager from PersistenceContext is shared by all classes in this project.
	// This means that it's possible that you cannot have a clean transaction when making updates. Thus this type
	// of entityManager is best used for data retrieval only

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaexampleApplication.class, args);
	}
	@PostConstruct
	public void start(){
		Employee e = new Employee();
		e.setAge(20);
		e.setName("Foo Bar");
		e.setSsn("1234");
		e.setDob(new Date());

		EntityManager entityManager = emf.createEntityManager();

		// NB: If you use the PersistenceContext to get an EntityManager you cannot use said entityManager in a
		// transaction. The following error occurs: Not allowed to create transaction on shared EntityManager
		// There is a workaround. During the annotation of the EntityManager do the following:
		// @PersistenceContext(type = PersistenceContextType.EXTENDED)
		// This means I as developer let spring know that I am going to manager threads and closer of used resources
		// I.e Transactions


		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(e);

		transaction.commit();
		entityManager.close();
	}
}
