package repository.note.impl;

import entity.Note;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repository.note.NoteRepository;
import repository.user.UserRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class NoteRepositoryImpl implements NoteRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Note> rowMapper;
    private final Properties properties;

    public NoteRepositoryImpl(DataSource dataSource, RowMapper<Note> rowMapper, Properties properties) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
        this.properties = properties;
    }

    @Override
    public long getCurId() {
        return jdbcTemplate.queryForObject(properties.getProperty("database.sql.notes.last-id"), Long.class);
    }

    @Override
    public Optional<Note> getById(Long id) {
        List<Note> notes = jdbcTemplate.query(properties.getProperty("database.sql.notes.get-by-id"), rowMapper, id);
        return optionalSingleResult(notes);
    }

    @Override
    public long size_user(Long id_user) {
        return jdbcTemplate.queryForObject(properties.getProperty("database.sql.notes.size-user"), Long.class, id_user);
    }

    @Override
    public List<Note> getAllByUser(Long id_user) {
        return jdbcTemplate.query(properties.getProperty("database.sql.notes.get-all-by-user"), rowMapper, id_user);
    }

    @Override
    public List<Note> getAllByUserLimitOffset(Long id_user, int limit, int offset) {
        return jdbcTemplate.query(properties.getProperty("database.sql.notes.get-by-id-limit-offset"), rowMapper, id_user, limit, offset);
    }

    @Override
    public List<Note> getAllByUserLimitOffsetD(Long id_user, int limit, int offset) {
        return jdbcTemplate.query(properties.getProperty("database.sql.notes.get-by-id-limit-offset-d"), rowMapper, id_user, limit, offset);
    }

    @Override
    public boolean add(Note note) {
        return jdbcTemplate.update(properties.getProperty("database.sql.notes.add"),
                note.getId_user(),
                note.getId_type(),
                note.getAuthor(),
                note.getTitle(),
                note.getText(),
                note.getDate(),
                note.getDisplayed_name()
                // and go on
                ) == 1;
    }

    @Override
    public List<Note> getAll() {
        return jdbcTemplate.query(properties.getProperty("database.sql.notes.get-all"), rowMapper);
    }

    @Override
    public boolean update(Note type) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update(properties.getProperty("database.sql.notes.delete"), id) == 1;
    }

    private Optional<Note> optionalSingleResult(List<Note> notes) {
        if (notes.isEmpty()) {
            return Optional.empty();
        } else {
            return notes.stream().findAny();
        }
    }
}
