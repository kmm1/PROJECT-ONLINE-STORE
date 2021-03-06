package servlet;

import dao.ActorDao;
import dao.MovieDao;
import dao.ProducerDao;
import dao.UserDao;
import entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

/**
 * Created by icons on 19.4.17.
 */
@WebServlet("/index")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listMovies", MovieDao.getInstance().findAllMovies());
        req.setAttribute("listActors", ActorDao.getInstance().findAllActors());
        req.setAttribute("listProducers", ProducerDao.getInstance().findAllProducers());
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String registrationLine = req.getParameter("prof");
        if (email.equals("") || password.equals("")) {
            requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp");
            requestDispatcher.forward(req, resp);
        }
        Optional<User> selectUserByEmailPassword = UserDao.getInstance().
                selectUserByEmailPassword(email, password);
        if (!selectUserByEmailPassword.isPresent()) {
            requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/index-wrong-password-user.jsp");
            requestDispatcher.forward(req, resp);
        } else if (selectUserByEmailPassword.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("userId", selectUserByEmailPassword.get().getId());
            session.setAttribute("userName", selectUserByEmailPassword.get().getName());
            session.setAttribute("userLastname", selectUserByEmailPassword.get().getLastname());
            session.setAttribute("userLoggedIn", true);
            if (selectUserByEmailPassword.get().getRole().equals("user")) {
                req.setAttribute("listMovies", MovieDao.getInstance().findAllMovies());
                req.setAttribute("listActors", ActorDao.getInstance().findAllActors());
                req.setAttribute("listProducers", ProducerDao.getInstance().findAllProducers());
                requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/user-content.jsp");
                requestDispatcher.forward(req, resp);
            }
            if (selectUserByEmailPassword.get().getRole().equals("admin")) {
                req.setAttribute("listMovies", MovieDao.getInstance().findAllMovies());
                req.setAttribute("listActors", ActorDao.getInstance().findAllActors());
                req.setAttribute("listProducers", ProducerDao.getInstance().findAllProducers());
                System.out.println(ActorDao.getInstance().findAllActors());
                requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/admin-content.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }
}
