package repository.user.impl;

import entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import repository.user.UserRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> rowMapper;
    private final Properties properties;

    public UserRepositoryImpl(DataSource dataSource, RowMapper<User> rowMapper, Properties properties) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.rowMapper = rowMapper;
        this.properties = properties;
    }

    @Override
    public Optional<User> getById(Long id) {
        List<User> users
                = jdbcTemplate.query(properties.getProperty("database.sql.get-by-id"), rowMapper, id);
        return optionalSingleResult(users
        );
    }

    @Override
    public Optional<User> getByGmail(String gmail) {
        List<User> users
                = jdbcTemplate.query(properties.getProperty("database.sql.get-by-gmail"), rowMapper, gmail);
        return optionalSingleResult(users
        );
    }

    @Override
    public boolean add(User type) {
        System.out.println("add " + type.getId_user() + " " + type.getUsername());
        return jdbcTemplate.update(properties.getProperty("database.sql.add"),
                type.getGmail(),
                type.getUsername(),
                type.getPassword(),
                type.getSalt()) == 1;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(properties.getProperty("database.sql.get-all"), rowMapper);
    }

    @Override
    public boolean update(User type) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcTemplate.update(properties.getProperty("database.sql.delete"), id) == 1;
    }

    private Optional<User> optionalSingleResult(List<User> users
    ) {
        if (users
                .isEmpty()) {
            return Optional.empty();
        } else {
            return users
                    .stream().findAny();
        }
    }
}
