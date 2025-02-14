package service.tag_note;

public interface TagNoteService {
    String getTagNamesByIdNote(long id_note);
    boolean addList(long id_note, String[] id_tags);
    boolean deleteNoteId(long id_note);
}
