package controller.add;
import entity.Note;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.note.NoteService;
import service.tag.TagService;
import service.tag_note.TagNoteService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "note", value = "/note")
public class NoteServlet extends HttpServlet {
    private NoteService noteService;
    private TagService tagService;
    private TagNoteService tagNoteService;
    final static Logger logger = LogManager.getLogger(NoteServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        noteService = (NoteService) servletContext.getAttribute("noteService");
        tagService = (TagService) servletContext.getAttribute("tagService");
        tagNoteService = (TagNoteService) servletContext.getAttribute("tagNoteService");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tags", tagService.getAll());
        logger.always().log(req.getParameter("chosen_tags"));

        HttpSession session = req.getSession();

        String type;
        if (req.getParameter("type") != null) {
            type = req.getParameter("type");
            session.setAttribute("type", type);
        }
        else {
            type = (String) session.getAttribute("type");
        }

        logger.always().log("get type: " + type);

        setTitles(req, type);

        if (req.getAttribute("edit") != null) {
            tagNoteService.deleteNoteId(Long.parseLong(req.getParameter("id_note")));
            noteService.delete(Long.parseLong(req.getParameter("id_note")));
            setAttributes(req);
            req.getRequestDispatcher("/template/note.ftl").forward(req, resp);
        }
        else {
            session.removeAttribute("recordInsertedSuccessfully");
            setDefaultAttributes(req);
            req.getRequestDispatcher("/template/note.ftl").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tags", tagService.getAll());
        logger.always().log(req.getParameter("chosen_tags"));
        HttpSession session = req.getSession();

        String type = (String) session.getAttribute("type");
        logger.always().log("post type: " + type);
        setTitles(req, type);

        if (session.getAttribute("recordInsertedSuccessfully") == null ) {
            String[] selectedTags = req.getParameterValues("tag");

            String author = req.getParameter("author");
            String title = req.getParameter("title");
            String text = req.getParameter("text");
            String s_date = req.getParameter("date");

            Long user = (Long) req.getAttribute("user");

            noteService.add(getType(type), getDate(s_date), user, author, title, text, getDisplayedName(type, title, author));
            tagNoteService.addList(noteService.getCurId(), selectedTags);

            session.setAttribute("recordInsertedSuccessfully","true");
            logger.always().log("post recordInsertedSuccessfully true " + text);
            req.getRequestDispatcher("main").forward(req, resp);
        }
        else {
            session.removeAttribute("recordInsertedSuccessfully");
            logger.always().log("post remove recordInsertedSuccessfully");
            req.getRequestDispatcher("main").forward(req, resp);
        }
    }

    private void setAttributes(HttpServletRequest req) {
        Note note = (Note) req.getAttribute("edit");
        req.setAttribute("text", note.getText());
        req.setAttribute("displayed_name", note.getDisplayed_name());
        req.setAttribute("date",new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(note.getDate().getTime())));
    }

    private void setDefaultAttributes(HttpServletRequest req) {
        req.setAttribute("text","");
        req.setAttribute("displayed_name", "");
        req.setAttribute("date","");
    }

    private void setTitles(HttpServletRequest req, String type) {
        String title;
        String display = "none";
        if (type.equals("book")) {
            title = "Book Information";
            display = "block";
        }
        else if (type.equals("movie"))  {
            title = "Movie Information";
        }
        else {
            title = "Music Information";
        }
        req.setAttribute("title", title);
        req.setAttribute("display", display);
    }

    private String getDisplayedName(String type, String title, String author) {
        if (type.equals("movie"))  {
            return title;
        }
        else {
            return title + " by " + author;
        }
    }

    private java.sql.Date getDate(String s_date) {
        java.util.Date utilDate = null;
        try {
            utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(s_date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new java.sql.Date(utilDate.getTime());
    }

    private long getType(String type) {
        if (type.equals("book")) {
            return 1L;
        }
        else if (type.equals("movie"))  {
            return 2L;
        }
        else {
            return 3L;
        }
    }
}
