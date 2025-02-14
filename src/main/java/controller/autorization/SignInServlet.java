package controller.autorization;

import entity.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import service.user.UserService;

import java.io.IOException;

@WebServlet(name = "signin", value = "/signin")
public class SignInServlet extends HttpServlet {
    private UserService userService;
    final static Logger logger = LogManager.getLogger(SignInServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();

        userService = (UserService) servletContext.getAttribute("userService");

        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.always().log("");
        req.getRequestDispatcher("/template/signin.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.always().log("");
        String gmail = req.getParameter("gmail");
        String password = req.getParameter("password");
        System.out.println(gmail + " " + password);

        if (validUser(gmail, password)) {
            // check password
            rememberUser(gmail, req, resp);
            System.out.println("try to main");
            //req.getRequestDispatcher("main").forward(req, resp);
            resp.sendRedirect("main");
        }
        else {
            String errorMessage = "Invalid email or password. Please try again.";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/template/signin.ftl").forward(req, resp);
        }
    }

    private boolean validUser(String gmail, String receivedPassword) {
        if (userService.hasGmail(gmail)) {
            User user = userService.getByGmail(gmail);
            return validPassword(user, receivedPassword);
        }
        return false;
    }

    private void rememberUser(String gmail, HttpServletRequest req, HttpServletResponse resp) {
        User user = userService.getByGmail(gmail);
        req.getSession().setAttribute("user", user);
        if (req.getParameter("check") != null) {
            logger.always().log("remember cookie");
            Cookie cookie = new Cookie("remember", String.valueOf(user.getId_user()));
            resp.addCookie(cookie);
        }
        req.setAttribute("user",user.getId_user());
    }

    private boolean validPassword(User user, String receivedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(receivedPassword + user.getSalt(), user.getPassword());
    }
}
