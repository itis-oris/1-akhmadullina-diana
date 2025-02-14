package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Tag {
    long id_tag;
    String name;
}
