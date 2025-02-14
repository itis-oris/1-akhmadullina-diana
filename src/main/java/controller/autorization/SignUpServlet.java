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
import java.util.UUID;

@WebServlet(name = "signup", value = "/signup")
public class SignUpServlet extends HttpServlet {
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
        //req.getRequestDispatcher("jsp/signup.jsp").forward(req, resp); /template/signin.ftl
        req.getRequestDispatcher("/template/signup.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.always().log("");
        String gmail = req.getParameter("gmail");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("got " + gmail + " " + username + " " + password);

        if (newGmail(gmail)) {
            User user = saveUser(gmail, username, password);
            rememberUser(user, req, resp);
            req.setAttribute("user",user.getId_user());
            System.out.println("try to main");
            resp.sendRedirect("main");
        }
        else {
            String errorMessage = "User with this email already exists.";
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("/template/signup.ftl").forward(req, resp);
        }
    }

    private String getSalt() {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    private String getHashPassword(String password, String salt) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password + salt);
    }

    private boolean newGmail(String gmail) {
        return !userService.hasGmail(gmail);
    }

    private User saveUser(String gmail, String username, String password) {
        String salt = getSalt();
        return userService.add(gmail, username, getHashPassword(password, salt), salt);
    }
    private void rememberUser(User user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        if (req.getParameter("check") != null) {
            Cookie cookie = new Cookie("remember", String.valueOf(user.getId_user()));
            resp.addCookie(cookie);
        }
    }
}