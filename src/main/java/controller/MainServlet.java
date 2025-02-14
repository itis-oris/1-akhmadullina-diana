package controller;

import entity.Note;
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
import service.tag.TagService;
import service.tag_note.TagNoteService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "main", value = "/main")
public class MainServlet extends HttpServlet {
    private NoteService noteService;
    private TagNoteService tagNoteService;
    private TagService tagService;
    private int page;
    private boolean desc;
    private final int DISPLAYED = 3;
    final static Logger logger = LogManager.getLogger(MainServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        noteService = (NoteService) servletContext.getAttribute("noteService");
        tagNoteService = (TagNoteService) servletContext.getAttribute("tagNoteService");
        tagService = (TagService) servletContext.getAttribute("tagService");
        page = 1;
        desc = true;
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.always().log("");
        displayNotes(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.always().log("");
        displayNotes(req, resp);
    }

    private void displayNotes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String user = (String) req.getAttribute("user");
//        Long user_id = Long.parseLong(user);

        Long user_id = (Long) req.getAttribute("user");

        //Long user_id = Long.valueOf((String) req.getAttribute("user"));
        if (req.getParameter("order") != null) {
            desc = !desc;
        }

        if (req.getParameter("move") != null) {
            String move = req.getParameter("move");
            if (move.equals("next")) {page ++;}
            else {page --;}
        }

        logger.always().log();

        long max_page = Math.max((long) Math.ceil( (double) noteService.sizeUser(user_id)/DISPLAYED), 1);

        page = Math.max(page, 1);
        page = Math.min(page, (int) max_page);

        int limit = DISPLAYED;
        int offset = (page-1)*DISPLAYED;

        logger.always().log("page {} max_page {} limit {} offset {}",page, max_page, limit, offset);

        //List<Note> notes = noteService.getAllByUserLimitOffset((String) req.getAttribute("user"), limit, offset);

        List<Note> notes;
        if (desc) {
            notes = noteService.getAllByUserLimitOffsetD(user_id, limit, offset);
        }
        else {
            notes = noteService.getAllByUserLimitOffset(user_id, limit, offset);
        }
        req.setAttribute("notes", notes);
        req.setAttribute("tagNoteService", tagNoteService);
        req.getRequestDispatcher("/template/history.ftl").forward(req, resp);
    }
}
