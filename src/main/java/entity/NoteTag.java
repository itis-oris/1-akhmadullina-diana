package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class NoteTag {
    long id_notetag;
    long id_note;
    long id_tag;
}