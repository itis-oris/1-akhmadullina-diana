package service.tag_note;

import lombok.AllArgsConstructor;
import repository.note_tag.NoteTagRepository;

@AllArgsConstructor
public class TagNoteServiceImpl implements TagNoteService {
    private NoteTagRepository noteTagRepository;

    @Override
    public String getTagNamesByIdNote(long id_note) {
        return  String.join(", ", noteTagRepository.getTagNamesByIdNote(id_note));
    }

    @Override
    public boolean addList(long id_note, String[] id_tags) {
        if (id_tags == null) {
            return false;
        }
        for (String s : id_tags) {
            long id_tag = Long.parseLong(s);
            noteTagRepository.add(id_note, id_tag);
        }
        return true;
    }

    @Override
    public boolean deleteNoteId(long id_note) {
        return noteTagRepository.deleteNoteId(id_note);
    }
}

