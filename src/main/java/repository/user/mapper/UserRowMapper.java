package repository.user.mapper;

import entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id_user(rs.getLong("id_user"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .salt(rs.getString("salt"))
                .build();
    }
}
