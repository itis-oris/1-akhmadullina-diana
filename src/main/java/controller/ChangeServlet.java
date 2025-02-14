package controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.note.NoteService;
import service.tag_note.TagNoteService;

import java.io.IOException;

@WebServlet(name = "delete", value = "/delete")
public class ChangeServlet extends HttpServlet {
    private NoteService noteService;
    private TagNoteService tagNoteService;
    final static Logger logger = LogManager.getLogger(ChangeServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        tagNoteService = (TagNoteService) servletContext.getAttribute("tagNoteService");
        noteService = (NoteService) servletContext.getAttribute("noteService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.always().log("get");
        doChange(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.always().log("post");
        doChange(req, resp);
    }

    private void doChange(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id_note = Long.parseLong(req.getParameter("id_note"));
        logger.always().log("post");
        if (req.getParameter("action").equals("delete")) {
            tagNoteService.deleteNoteId(id_note);
            noteService.delete(id_note);
            req.getRequestDispatcher("main").forward(req, resp);
        }
        else {
            req.setAttribute("edit", noteService.getById(id_note));
            req.getRequestDispatcher("note").forward(req, resp);
        }
    }
}
