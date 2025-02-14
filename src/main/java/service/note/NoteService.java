package service.note;

import entity.Note;

import java.sql.Date;
import java.util.List;

public interface NoteService {
    boolean addBook(Note note);
    List<Note> getAll();
    Note getById(long id);
    boolean add(long type, Date date, Long id_user, String author, String title, String text, String displayed_name);
    boolean addBook(Date date, Long id_user, String author, String title, String text, String displayed_name);
    boolean addMovie(Date date, Long id_user, String title, String text, String displayed_name);
    boolean addMusic(Date date, Long id_user, String author, String title, String text, String displayed_name);
    boolean delete(long id);
    List<Note> getAllByUser(Long id_user);
    List<Note> getAllByUserLimitOffset(Long id_user, int limit, int offset);
    List<Note> getAllByUserLimitOffsetD(Long id_user, int limit, int offset);
    long sizeUser(Long id_user);
    String getTagsAsString(long note_id);
    long getCurId();
}
