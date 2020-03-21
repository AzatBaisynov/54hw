package servlet;

import db.ApplicationDb;
import model.Application;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/accept-application")
public class ApplicationUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("applicationId"));
        System.out.println(id);
        ApplicationDb applicationDb = new ApplicationDb();
        applicationDb.updateApplication(id);
        System.out.println(applicationDb.updateApplication(id));

        req.getRequestDispatcher("/done.jsp").forward(req, resp);
    }
}
