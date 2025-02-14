package service.note;

import entity.Note;
import entity.User;
import lombok.AllArgsConstructor;
import repository.note.NoteRepository;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private NoteRepository noteRepository;

    @Override
    public boolean addBook(Note note) {
        return noteRepository.add(note);
    }

    @Override
    public boolean addBook(Date date, Long id_user, String author, String title, String text, String displayed_name) {
        Note note = Note.builder()
                .id_user(id_user)
                .id_type(1L)
                .author(author)
                .title(title)
                .text(text)
                .date(date)
                .displayed_name(displayed_name)
                .build();
        return noteRepository.add(note);
    }

    @Override
    public boolean addMovie(Date date, Long id_user, String title, String text, String displayed_name) {
        Note note = Note.builder()
                .id_user(id_user)
                .id_type(2L)
                .title(title)
                .text(text)
                .date(date)
                .displayed_name(displayed_name)
                .build();
        return noteRepository.add(note);
    }

    @Override
    public boolean addMusic(Date date, Long id_user, String author, String title, String text, String displayed_name) {
        Note note = Note.builder()
                .id_user(id_user)
                .id_type(3L)
                .author(author)
                .title(title)
                .text(text)
                .date(date)
                .displayed_name(displayed_name)
                .build();
        return noteRepository.add(note);
    }

    @Override
    public boolean delete(long id) {
        return noteRepository.delete(id);
    }

    @Override
    public List<Note> getAllByUser(Long id_user) {
        return noteRepository.getAllByUser(id_user);
    }

    @Override
    public List<Note> getAllByUserLimitOffset(Long id_user, int limit, int offset) {
        return noteRepository.getAllByUserLimitOffset(id_user, limit, offset);
    }

    @Override
    public List<Note> getAllByUserLimitOffsetD(Long id_user, int limit, int offset) {
        return noteRepository.getAllByUserLimitOffsetD(id_user, limit, offset);
    }

    @Override
    public long sizeUser(Long id_user) {
        return noteRepository.size_user(id_user);
    }

    @Override
    public String getTagsAsString(long note_id) {
        return "tags";
    }

    @Override
    public long getCurId() {
        return noteRepository.getCurId();
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.getAll();
    }

    @Override
    public Note getById(long id) {
        return noteRepository.getById(id).isPresent() ? noteRepository.getById(id).get() : null;
    }

    @Override
    public boolean add(long type, Date date, Long id_user, String author, String title, String text, String displayed_name) {
        Note note = Note.builder()
                .id_user(id_user)
                .id_type(type)
                .author(author)
                .title(title)
                .text(text)
                .date(date)
                .displayed_name(displayed_name)
                .build();
        return noteRepository.add(note);
    }
}

