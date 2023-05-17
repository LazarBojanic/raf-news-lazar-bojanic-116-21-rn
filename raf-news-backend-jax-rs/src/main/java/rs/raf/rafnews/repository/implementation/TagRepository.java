package rs.raf.rafnews.repository.implementation;

import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.repository.specification.ITagRepository;

import java.util.List;

@RequestScoped
public class TagRepository implements ITagRepository {
    @Override
    public List<Tag> getAllTags() {
        return null;
    }

    @Override
    public Tag getTagById(Integer id) {
        return null;
    }

    @Override
    public Tag addTag(Tag article) {
        return null;
    }

    @Override
    public Tag updateTag(Tag article) {
        return null;
    }

    @Override
    public boolean deleteTagById(Integer id) {
        return false;
    }
}
