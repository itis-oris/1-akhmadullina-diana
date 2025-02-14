package filter;

import entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.user.UserService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter {
    UserService userService;
    final static Logger logger = LogManager.getLogger(AuthenticationFilter.class);
    @Override
    public void init(FilterConfig config) throws ServletException {
        ServletContext filterContext = config.getServletContext();
        userService = (UserService) filterContext.getAttribute("userService");

    }
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        logger.always().log(req.getServletPath());
        if (req.getServletPath().startsWith("/signin") ||
                req.getServletPath().startsWith("/signup")
        ) {chain.doFilter(req, res);}
        else {
            // if there is user in session let it go
            if (req.getSession().getAttribute("user") != null) {
                logger.always().log("have user");
                req.setAttribute("user", ((User) req.getSession().getAttribute("user")).getId_user());
                chain.doFilter(req, res);
            }
            else {
                logger.always().log("no user");
                Optional<Cookie> cookie =
                        Arrays.stream(req.getCookies())
                                .filter(this::validCookie).findFirst();
                if (cookie.isPresent() && cookie.get().getName().equals("remember")) {
                    logger.always().log("cookie");
                    req.setAttribute("user", cookie.get().getValue());
                    if (req.getServletPath().equals("/")) {
                        logger.always().log(req.getServletPath());
                        //req.getRequestDispatcher("main").forward(req, res);
                        res.sendRedirect("main");
                    }
                    else {
                        chain.doFilter(req, res);
                    }
                }
                else {
                    logger.always().log("nothing");
                    req.getRequestDispatcher("/template/signin.ftl").forward(req, res);
                }
            }
        }
    }
    private boolean validCookie(Cookie c) {
        return c.getName().equals("remember") && userService.hasId(Long.valueOf(c.getValue()));
    }
}
