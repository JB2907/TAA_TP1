package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Date;
import java.util.List;

public class DAO {

    private EntityManager manager;

    public DAO(EntityManager manager) {
        this.manager = manager;
    }

    public List<Client> createClients() {
        Client c1 = new Client("user5@gmail", "user5MDP", "Eleonore");
        Client c2 = new Client("user6@gmail", "user6MDP", "Florent");
        Client c3 = new Client("user7@gmail", "user7MDP", "Gnou");
        Client c4 = new Client("user8@gmail", "user8MDP", "Hibou");

        manager.persist(c1);
        manager.persist(c2);
        manager.persist(c3);
        manager.persist(c4);

        return List.of(c1, c2, c3, c4);
    }

    public List<Professional> createProfessionals() {
        Professional p1 = new Professional("user1@gmail.com", "user1MDP", "Alice");
        Professional p2 = new Professional("user2@gmail.com", "user2MDP", "Bob");
        Professional p3 = new Professional("user3@gmail.com", "user3MDP", "Charlie");
        Professional p4 = new Professional("user4@gmail.com", "user4MDP", "Dalmatien");

        manager.persist(p1);
        manager.persist(p2);
        manager.persist(p3);
        manager.persist(p4);

        return List.of(p1, p2, p3, p4);
    }

    public RDV createAppointment(Date start_time, int duration, String name, Professional professional, Client client) {
        RDV res;
        if (client == null) {
            res = new RDV(start_time, duration, name, professional);
            professional.getAppointments().add(res);
            manager.merge(professional);
        } else {
            res = new RDV(start_time, duration, name, professional, client);
            professional.getAppointments().add(res);
            manager.merge(professional);
            client.getAppointments().add(res);
            manager.merge(client);
        }
        return res;
    }

    public List<RDV> createAppointments(List<Client> clients, List<Professional> professionals) {

        RDV r1 = createAppointment(new Date(), 60, "Docteur", professionals.get(0), clients.get(3));
        RDV r2 = createAppointment(new Date(), 30, "Finances", professionals.get(0), clients.get(2));
        RDV r3 = createAppointment(new Date(), 20, "Ecole", professionals.get(1), clients.get(3));
        RDV r4 = createAppointment(new Date(), 50, "Informatique", professionals.get(2), null);

        manager.persist(r1);
        manager.persist(r2);
        manager.persist(r3);
        manager.persist(r4);

        return List.of(r1, r2, r3, r4);
    }

    public void getRDVFromClient(Client c) {
        List<RDV> queryResult = manager.createQuery("SELECT r FROM RDV r WHERE r.client = :client", RDV.class).setParameter("client", c).getResultList();
        for (RDV r : queryResult) {
            System.out.println("Le RDV du client " + r.getClient().getName() + " intitulé " + r.getName() + " aura lieu à " + r.getStart_time() + " avec le pro " + r.getProfessional().getName());
        }
    }

    public void getRDVFromProfessional(Professional p) {
        TypedQuery<RDV> q = manager.createNamedQuery("getRDVFromAProfessional", RDV.class);
        q.setParameter("pro", p);
        for (RDV r : q.getResultList()) {
            System.out.println("Le RDV du professionnel " + r.getProfessional().getName() + " intitulé " + r.getName() + " aura lieu à " + r.getStart_time());
        }
    }

    public void getAvailableRDVFromProfessional(Professional p) {
        List<RDV> queryResult = manager.createQuery("SELECT r FROM RDV r WHERE r.professional = :pro AND NOT r.reserved", RDV.class).setParameter("pro", p).getResultList();
        for (RDV r : queryResult) {
            System.out.println("Le RDV " + r.getName() + " est disponible à " + r.getStart_time());
        }
    }

    public List<User> getAllUsers(){
        List<User> listUsers = manager.createQuery("select u from User u", User.class).getResultList();
        System.out.println("num of users: "+ listUsers.size());

        for(User user: listUsers){
            System.out.println("user: "+user.getName());
        }

        return listUsers;
    }

    public void createProfessional(String email, String password, String name) {
        User newProfessional = new Professional(email, password, name);
        manager.persist(newProfessional);
    }

    public void createClient(String email, String password, String name) {
        User newClient = new Client(email, password, name);
        manager.persist(newClient);
    }

    public void getAllClient() {
        List<Client> listClient = manager.createQuery("select c from Client c", Client.class).getResultList();

        for(Client client: listClient){
            System.out.println("user: " + client.getName());
        }
    }

    public void getAllProfessionals() {
        List<Professional> listProfessional = manager.createQuery("select p from Professional p", Professional.class).getResultList();

        for(Professional professional: listProfessional){
            System.out.println("user: " + professional.getName());
        }
    }

    public List<RDV> getAllRDVs() {
        return manager.createQuery("select r from RDV r", RDV.class).getResultList();
    }
}
