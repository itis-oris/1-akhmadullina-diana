package controller.add;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.note.NoteService;

import java.io.IOException;

@WebServlet(name = "add", value = "/add")
public class AddServlet extends HttpServlet {
    private NoteService noteService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        noteService = (NoteService) servletContext.getAttribute("noteService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("add get");
        req.getRequestDispatcher("/template/add.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("add post");
        System.out.println();
        req.getRequestDispatcher("/template/add.ftl").forward(req, resp);
    }
}
