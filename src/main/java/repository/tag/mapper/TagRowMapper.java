package repository.tag.mapper;

import entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TagRowMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Tag.builder()
                .id_tag(rs.getLong("id_tag"))
                .name(rs.getString("name"))
                .build();
    }
}
