package rs.raf.rafnews.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.TagDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.ExceptionMessage;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Category;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.repository.specification.ITagRepository;
import rs.raf.rafnews.service.specification.ITagService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class TagService implements ITagService {
    @Inject
    private ITagRepository tagRepository;

    @Override
    public List<Tag> getAllRawTags() throws GetException, JsonProcessingException {
        return this.tagRepository.getAllRawTags();
    }

    @Override
    public List<TagDto> getAllTags() throws GetException, JsonProcessingException {
        return this.tagRepository.getAllTags();
    }

    @Override
    public List<Tag> getAllRawTagsByArticleId(Integer articleId) throws GetException, JsonProcessingException {
        return this.tagRepository.getAllRawTagsByArticleId(articleId);
    }

    @Override
    public List<TagDto> getAllTagsByArticleId(Integer articleId) throws GetException, JsonProcessingException {
        return this.tagRepository.getAllTagsByArticleId(articleId);
    }

    @Override
    public TagDto joinTag(Tag tag) {
        return this.tagRepository.joinTag(tag);
    }

    @Override
    public Tag getRawTagById(Integer id) throws GetException, JsonProcessingException {
        return this.tagRepository.getRawTagById(id);
    }

    @Override
    public TagDto getTagById(Integer id) throws GetException, JsonProcessingException {
        return this.tagRepository.getTagById(id);
    }

    @Override
    public Tag getRawTagByTagName(String tagName) throws GetException, JsonProcessingException {
        return this.tagRepository.getRawTagByTagName(tagName);
    }

    @Override
    public TagDto getTagByTagName(String tagName) throws GetException, JsonProcessingException {
        return this.tagRepository.getTagByTagName(tagName);
    }

    @Override
    public Tag addRawTag(Tag tag) throws AddException, JsonProcessingException {
        return this.tagRepository.addRawTag(tag);
    }

    @Override
    public TagDto addTag(Tag tag) throws AddException, JsonProcessingException {
        return this.tagRepository.addTag(tag);
    }

    @Override
    public List<TagDto> addTagList(List<Tag> tagList) throws AddException, JsonProcessingException {
        return this.tagRepository.addTagList(tagList);
    }

    @Override
    public Integer updateTagById(Integer id, Tag tag) {
        return this.tagRepository.updateTagById(id, tag);
    }

    @Override
    public Integer deleteTagById(Integer id) {
        return this.tagRepository.deleteTagsById(id);
    }
}
