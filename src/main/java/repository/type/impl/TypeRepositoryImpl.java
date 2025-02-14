package repository.type.impl;

import entity.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repository.type.TypeRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class TypeRepositoryImpl implements TypeRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Type> rowMapper;
    private final Properties properties;

    public TypeRepositoryImpl(DataSource dataSource, RowMapper<Type> rowMapper, Properties properties) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
        this.properties = properties;
    }

    @Override
    public Optional<Type> getById(Long id) {
        List<Type> notes = jdbcTemplate.query(properties.getProperty("database.sql.get-by-id"), rowMapper, id);
        return optionalSingleResult(notes);
    }

    private Optional<Type> optionalSingleResult(List<Type> notes) {
        if (notes.isEmpty()) {
            return Optional.empty();
        } else {
            return notes.stream().findAny();
        }
    }
}
