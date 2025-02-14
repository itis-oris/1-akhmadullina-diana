package repository.tag;

import entity.Note;
import entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    Optional<Tag> getById(Long id);
    List<Tag> getAll();
}
