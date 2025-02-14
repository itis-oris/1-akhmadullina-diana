package repository.note_tag.mapper;

import entity.NoteTag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class NoteTagRowMapper implements RowMapper<NoteTag> {

    @Override
    public NoteTag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return NoteTag.builder()
                .id_notetag(rs.getLong("id_notetag"))
                .id_note(rs.getLong("id_note"))
                .id_tag(rs.getLong("id_tag"))
                .build();
    }
}