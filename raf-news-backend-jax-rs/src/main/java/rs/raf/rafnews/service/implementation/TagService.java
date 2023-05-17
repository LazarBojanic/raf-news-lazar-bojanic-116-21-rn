package rs.raf.rafnews.service.implementation;

import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.service.specification.ITagService;

import java.util.List;

@RequestScoped
public class TagService implements ITagService {
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
