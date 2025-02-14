package repository.tag.impl;

import entity.Note;
import entity.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repository.tag.TagRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class TagRepositoryImpl implements TagRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Tag> rowMapper;
    private final Properties properties;

    public TagRepositoryImpl(DataSource dataSource, RowMapper<Tag> rowMapper, Properties properties) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
        this.properties = properties;
    }

    @Override
    public Optional<Tag> getById(Long id) {
        return optionalSingleResult(jdbcTemplate.query(properties.getProperty("database.sql.tags.get-by-id"), rowMapper, id));
    }

    @Override
    public List<Tag> getAll() {
        return jdbcTemplate.query(properties.getProperty("database.sql.tags.get-all"), rowMapper);
    }

    private Optional<Tag> optionalSingleResult(List<Tag> notes) {
        if (notes.isEmpty()) {
            return Optional.empty();
        } else {
            return notes.stream().findAny();
        }
    }
}
