package repository.note_tag.impl;

import entity.Note;
import entity.NoteTag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repository.note_tag.NoteTagRepository;
import repository.tag.TagRepository;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class NoteTagRepositoryImpl implements NoteTagRepository {

    private final TagRepository tagRepository;
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<NoteTag> rowMapper;
    private final Properties properties;

    public NoteTagRepositoryImpl(TagRepository tagRepository, DataSource dataSource, RowMapper<NoteTag> rowMapper, Properties properties) {
        this.tagRepository = tagRepository;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
        this.properties = properties;
    }

    @Override
    public List<String> getTagNamesByIdNote(long id_note) {
        List<String> tagNames = new LinkedList<>();
        List<NoteTag> tagNotes = jdbcTemplate.query(properties.getProperty("database.sql.note_tags.get-all-by-note_id"), rowMapper, id_note);
        tagNotes.forEach(
                tn -> tagNames.add(tagRepository.getById(tn.getId_tag()).get().getName())
        );
        return tagNames;
    }

    @Override
    public boolean add(long id_note, long id_tag) {
        return jdbcTemplate.update(properties.getProperty("database.sql.note_tags.add"),id_note, id_tag) == 1;
    }

    @Override
    public boolean deleteNoteId(long id_note) {
        return jdbcTemplate.update(properties.getProperty("database.sql.note_tags.delete"), id_note) == 1;
    }
}
