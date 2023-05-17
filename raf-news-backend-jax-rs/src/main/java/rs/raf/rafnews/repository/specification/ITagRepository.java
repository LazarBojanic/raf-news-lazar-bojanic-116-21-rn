package rs.raf.rafnews.repository.specification;

import rs.raf.rafnews.model.Tag;

import java.util.List;

public interface ITagRepository {
    List<Tag> getAllTags();
    Tag getTagById(Integer id);
    Tag addTag(Tag article);
    Tag updateTag(Tag article);
    boolean deleteTagById(Integer id);
}