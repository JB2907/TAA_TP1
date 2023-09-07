package jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Date;

public class JpaTest {


	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try
		{
			Professional p = new Professional("admin@admin.org", "admin123", "admin");
			manager.persist(p);

			Client c = new Client("clientmail", "clientpw", "client");
			manager.persist(c);

			RDV r = new RDV();
			r.setDuration(30);
			r.setName("Finances");
			r.setProfessional(p);
			r.setStart_time(new Date());
			r.setClient(c);
			manager.persist(r);
//			User u = new User("admin", "admin123");
//			manager.persist(u);
//			User u2 = new User("admin", "admin123");
//			manager.persist(u2);
			// TODO create and persist entity
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

			
   	 	manager.close();
		factory.close();
	}




}
