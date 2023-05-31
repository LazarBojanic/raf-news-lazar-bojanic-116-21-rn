package rs.raf.rafnews.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.DeleteException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.UpdateException;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.repository.specification.ITagRepository;
import rs.raf.rafnews.service.specification.ITagService;

import java.sql.SQLException;
import java.util.List;
@RequestScoped
public class TagService implements ITagService {
    @Inject
    private ITagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() throws GetException, JsonProcessingException, SQLException {
        return this.tagRepository.getAllTags();
    }

    @Override
    public Tag getTagById(Integer id) throws JsonProcessingException, GetException, SQLException {
        return this.tagRepository.getTagById(id);
    }

    @Override
    public Tag getTagByTagName(String tagName) throws GetException, JsonProcessingException, SQLException {
        return this.tagRepository.getTagByTagName(tagName);
    }

    @Override
    public List<Tag> getTagListByTagNameList(List<String> tagNameList) throws GetException, JsonProcessingException, SQLException {
        return this.tagRepository.getTagListByTagNameList(tagNameList);
    }

    @Override
    public Tag addTag(String tagName) throws SQLException, AddException, JsonProcessingException, GetException {
        return this.tagRepository.addTag(tagName);
    }

    @Override
    public List<Tag> addTagList(List<String> tagNameList) throws SQLException, AddException, JsonProcessingException, GetException {
        return this.tagRepository.addTagList(tagNameList);
    }

    @Override
    public Integer updateTagById(Integer id, String tagName) throws SQLException, JsonProcessingException, UpdateException {
        return this.tagRepository.updateTagById(id, tagName);
    }

    @Override
    public Integer deleteTagById(Integer id) throws JsonProcessingException, DeleteException, SQLException {
        return this.tagRepository.deleteTagById(id);
    }
}
