package service.tag;

import entity.Tag;
import lombok.AllArgsConstructor;
import repository.tag.TagRepository;

import java.util.List;

@AllArgsConstructor
public class TagServiceImpl implements TagService{
    private TagRepository tagRepository;
    @Override
    public List<Tag> getAll() {
        return tagRepository.getAll();
    }
}
