package io.javabrains.springbootjpaexample;

import io.javabrains.springbootjpaexample.models.Employee;
import io.javabrains.springbootjpaexample.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

@SpringBootApplication
@EnableTransactionManagement
public class SpringbootjpaexampleApplication {
	/*
	@PersistenceUnit
	private EntityManagerFactory emf;
	*/

	// NB: It's possible in spring boot to avoid having to create the abover EntityManagerFactory
	// You can get the EntityManager and annotate it as Persistence Context directly as follows
	/*
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	*/
	// BUT there is a GOTCHA. The entityManager from PersistenceContext is shared by all classes in this project.
	// This means that it's possible that you cannot have a clean transaction when making updates. Thus this type
	// of entityManager is best used for data retrieval only


	// Moving onto using CrudRepository or shall I say, my EmployeeRepository which extencs CrudRepository
	@Autowired
	EmployeeRepository employeeRepository;
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

		//EntityManager entityManager = emf.createEntityManager();

		// NB: If you use the PersistenceContext to get an EntityManager you cannot use said entityManager in a
		// transaction. The following error occurs: Not allowed to create transaction on shared EntityManager
		// There is a workaround. During the annotation of the EntityManager do the following:
		// @PersistenceContext(type = PersistenceContextType.EXTENDED)
		// This means I as developer let spring know that I am going to manager threads and closer of used resources
		// I.e Transactions


		/*
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.persist(e);

		transaction.commit();
		entityManager.close();
		*/

		saveEmployee(e);

		Optional<Employee> employee = employeeRepository.findById(1);
		if(employee.isPresent()){
			System.out.println(employee.get());
			updateEmployee(employee.get());
		}


	}

	// As opposed to creating a transaction, beginning, committing and then closing said transaction, you simply
	// mark this method as @Transactional
	@Transactional()
	private void updateEmployee(Employee employee) {
		employee.setName("Updated Name");
		employeeRepository.save(employee);
	}

	@Transactional
	private void saveEmployee(Employee employee){
		employeeRepository.save(employee);
	}
}
