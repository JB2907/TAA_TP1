package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import jpa.User;

@WebServlet(name="servletuser",
        urlPatterns={"/user"})
public class ServletUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        DAO dao = new DAO(manager);
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        dao.createClients();
        dao.createProfessionals();

        tx.commit();

        List<User> users = dao.getAllUsers();

        PrintWriter p = new PrintWriter(resp.getOutputStream());

        String userDisplay = "<table>" +
                "<thead>" +
                "<tr>" +
                "<th>id</th>"+
                "<th>nom</th>"+
                "<th>email</th>"+ "</tr> </thead> <tbody>"
        ;


        for(int i=0; i<users.size(); i++){
            System.out.print(users.get(i).getName());

            userDisplay = userDisplay+"<tr>"+
                    "<td>"+users.get(i).getId()+"</td>"+
                    "<td>"+users.get(i).getName()+"</td>"+
                    "<td>"+users.get(i).getEmail()+"</td>"+"</tr>";
        }

        userDisplay = userDisplay+ "</tbody> </table>";

        p.print("<HTML>\n<BODY>\n" +
                "<H1>Test</H1>\n" +
                userDisplay+
                        "</BODY></HTML>");
        p.flush();

    }
}