package repository.note.mapper;

import entity.Note;
import entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


public class NoteRowMapper implements RowMapper<Note> {

    @Override
    public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Note.builder()
                .id_note(rs.getLong("id_note"))
                .id_user(rs.getLong("id_user"))
                .id_type(rs.getLong("id_type"))
                .title(rs.getString("title"))
                .text(rs.getString("text"))
                .date((Date) rs.getObject("date"))
                .displayed_name(rs.getString("displayed_name"))
                .build();
    }
}