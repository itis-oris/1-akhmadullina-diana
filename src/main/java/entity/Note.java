package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Builder
@Getter
@Setter
public class Note {
    long id_note;
    long id_user;
    long id_type;
    String title;
    String text;
    long id_tag;
    String picture;
    Date date;
    String link;
    String author;
    String album;
    String artist;
    String displayed_name;
}