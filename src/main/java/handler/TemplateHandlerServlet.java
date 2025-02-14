package handler;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.note.NoteService;
import service.tag.TagService;
import service.tag_note.TagNoteService;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet("*.ftl")
public class TemplateHandlerServlet extends HttpServlet {
    private static Map<String, String> templates;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        templates = (Map<String, String>) servletContext.getAttribute("templates");
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        add(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        add(request, response);
    }

    private static void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var cfg = new Configuration(new Version("2.3.31"));
        cfg.setClassForTemplateLoading(TemplateHandlerServlet.class, "/template");
        cfg.setDefaultEncoding("UTF-8");

        System.out.println("template " + request.getServletPath());

        Template template =  cfg.getTemplate(templates.get(request.getServletPath()));

        Map<String, Object> data = new HashMap<>();
        InputStream inputStream = TemplateHandlerServlet.class.getClassLoader().getResourceAsStream(request.getServletPath());
        byte[] content = inputStream.readAllBytes();
        String contentString = new String(content);
        Iterator<String> iter = request.getAttributeNames().asIterator();

        while (iter.hasNext()) {
            String name = iter.next();
            if (contentString.contains(name.strip())) {
                data.put(name, request.getAttribute(name));
            }
        }

        try (StringWriter out = new StringWriter()) {
            template.process(data, out);
            Writer fileWriter = response.getWriter();
            try {
                template.process(data, fileWriter);
            } finally {
                fileWriter.close();
            }
            out.flush();
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

}
