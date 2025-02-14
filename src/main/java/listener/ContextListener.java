package listener;

import configuration.DataSourceConfiguration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import repository.note.NoteRepository;
import repository.note.impl.NoteRepositoryImpl;
import repository.note.mapper.NoteRowMapper;
import repository.note_tag.NoteTagRepository;
import repository.note_tag.impl.NoteTagRepositoryImpl;
import repository.note_tag.mapper.NoteTagRowMapper;
import repository.tag.TagRepository;
import repository.tag.impl.TagRepositoryImpl;
import repository.tag.mapper.TagRowMapper;
import repository.type.TypeRepository;
import repository.type.impl.TypeRepositoryImpl;
import repository.type.mapper.TypeRowMapper;
import repository.user.UserRepository;
import repository.user.impl.UserRepositoryImpl;
import repository.user.mapper.UserRowMapper;
import service.note.NoteService;
import service.note.NoteServiceImpl;
import service.tag.TagService;
import service.tag.TagServiceImpl;
import service.tag_note.TagNoteService;
import service.tag_note.TagNoteServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;


@WebListener
public class ContextListener implements ServletContextListener {
    final static Logger logger = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataSourceConfiguration configuration =
                new DataSourceConfiguration(properties);

        UserRepository userRepository =
                new UserRepositoryImpl(configuration.hikariDataSource(),new UserRowMapper(), properties);
        NoteRepository noteRepository =
                new NoteRepositoryImpl(configuration.hikariDataSource(),new NoteRowMapper(), properties);
        TypeRepository typeRepository =
                new TypeRepositoryImpl(configuration.hikariDataSource(),new TypeRowMapper(), properties);
        TagRepository tagRepository = new TagRepositoryImpl(configuration.hikariDataSource(),new TagRowMapper(), properties);
        NoteTagRepository noteTagRepository = new NoteTagRepositoryImpl(tagRepository, configuration.hikariDataSource(),new NoteTagRowMapper(), properties);

        UserService userService = new UserServiceImpl(userRepository);
        NoteService noteService = new NoteServiceImpl(noteRepository);
        TagNoteService tagNoteService = new TagNoteServiceImpl(noteTagRepository);
        TagService tagService = new TagServiceImpl(tagRepository);

        Map<String, String> templates = properties.entrySet().stream()
                .filter(e -> e.getKey().toString().startsWith("/template"))
                .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("noteService", noteService);
        servletContext.setAttribute("tagNoteService", tagNoteService);
        servletContext.setAttribute("tagService", tagService);
        servletContext.setAttribute("templates", templates);
    }
}
