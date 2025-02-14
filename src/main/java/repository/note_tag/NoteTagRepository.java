package repository.note_tag;
import entity.Note;
import repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface NoteTagRepository {
    List<String> getTagNamesByIdNote(long id_note);
    boolean add(long id_note, long id_tag);
    boolean deleteNoteId(long id_note);
}
