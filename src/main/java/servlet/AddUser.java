package servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpa.DAO;

import java.io.IOException;

@WebServlet(name="adduser",
        urlPatterns={"/addUser"})
public class AddUser extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();

        DAO dao = new DAO(manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        if (request.getParameter("status").equals("professional")) {
            dao.createProfessional(request.getParameter("email"), request.getParameter("password"), request.getParameter("name"));
        } else {
            dao.createClient(request.getParameter("email"), request.getParameter("password"), request.getParameter("name"));
        }

        tx.commit();
    }
}
