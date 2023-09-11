package jpa;


import jakarta.persistence.*;

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
		DAO dao = new DAO(manager);
		try {
			List<Client> clients = dao.createClients();
			List<Professional> professionals = dao.createProfessionals();
			List<RDV> appointments = dao.createAppointments(clients, professionals);

			dao.getAllUsers();

			dao.getRDVFromClient(clients.get(3));
			dao.getAvailableRDVFromProfessional(professionals.get(2));

			// Using a Named Query
			dao.getRDVFromProfessional(professionals.get(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();


		manager.close();
		factory.close();
	}
}
