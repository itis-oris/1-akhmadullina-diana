package repository.note;
import entity.Note;
import entity.User;
import repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface NoteRepository extends CrudRepository<Note, Long> {
    long getCurId();
    Optional<Note> getById(Long id);
    long size_user(Long id_user);
    List<Note> getAllByUser(Long id_user);
    List<Note> getAllByUserLimitOffset(Long id_user, int limit, int offset);
    List<Note> getAllByUserLimitOffsetD(Long id_user, int limit, int offset);
}
