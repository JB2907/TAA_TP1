package jpa;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Date;
import java.util.List;

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
		JpaTest jpa = new JpaTest(manager);
		try
		{
			jpa.createUsersAndRDV();

			jpa.listUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

			
   	 	manager.close();
		factory.close();
	}


	public void createUsersAndRDV() {
		Professional p1 = new Professional("user1@gmail.com", "user1MDP", "Alice");
		Professional p2 = new Professional("user2@gmail.com", "user2MDP", "Bob");
		Professional p3 = new Professional("user3@gmail.com", "user3MDP", "Charlie");
		Professional p4 = new Professional("user4@gmail.com", "user4MDP", "Dalmatien");

		Client c1 = new Client("user5@gmail", "user5MDP", "Eleonore");
		Client c2 = new Client("user6@gmail", "user6MDP", "Florent");
		Client c3 = new Client("user7@gmail", "user7MDP", "Gnou");
		Client c4 = new Client("user8@gmail", "user8MDP", "Hibou");

		RDV r1 = new RDV(new Date(), 60, "Docteur", p1, c4);
		RDV r2 = new RDV(new Date(), 30, "Finances", p1, c3);
		RDV r3 = new RDV(new Date(), 20, "Ecole", p2, c4);
		RDV r4 = new RDV(new Date(), 50, "Informatique", p3, c2);

		c2.getAppointments().add(r4);
		c3.getAppointments().add(r2);
		c4.getAppointments().add(r1);
		c4.getAppointments().add(r3);

		p1.getAppointments().add(r1);
		p1.getAppointments().add(r2);
		p2.getAppointments().add(r3);
		p3.getAppointments().add(r4);

		manager.persist(c1);
		manager.persist(c2);
		manager.persist(c3);
		manager.persist(c4);

		manager.persist(p1);
		manager.persist(p2);
		manager.persist(p3);
		manager.persist(p4);

		manager.persist(r1);
		manager.persist(r2);
		manager.persist(r3);
		manager.persist(r4);

		getRDVFromClient(c4);

	}

	public void getRDVFromClient(Client c) {
		List<RDV> queryResult = manager.createQuery("SELECT r FROM RDV r WHERE r.client = :client", RDV.class).setParameter("client", c).getResultList();
		for (RDV r : queryResult) {
			System.out.println(r.getDuration());
		}
	}

	public void listUser(){
		List<User> listUsers = manager.createQuery("select u from User u", User.class).getResultList();
		System.out.println("num of users: "+ listUsers.size());

		for(User user: listUsers){
			System.out.println("user: "+user.getName());
		}
	}

}
