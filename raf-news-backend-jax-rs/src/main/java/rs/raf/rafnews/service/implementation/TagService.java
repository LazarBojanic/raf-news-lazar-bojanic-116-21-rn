package rs.raf.rafnews.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.DeleteException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.UpdateException;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.service.specification.ITagService;

import java.sql.SQLException;
import java.util.List;
@RequestScoped
public class TagService implements ITagService {
    @Override
    public List<Tag> getAllTags() throws GetException, JsonProcessingException, SQLException {
        return null;
    }

    @Override
    public Tag getTagById(Integer id) throws JsonProcessingException, GetException, SQLException {
        return null;
    }

    @Override
    public Tag getTagByTagName(String tagName) throws GetException, JsonProcessingException, SQLException {
        return null;
    }


    @Override
    public Tag addTag(Tag tag) throws SQLException, AddException, JsonProcessingException {
        return null;
    }

    @Override
    public List<Tag> addTagList(List<Tag> tagList) throws SQLException, AddException, JsonProcessingException {
        return null;
    }


    @Override
    public Integer updateTagById(Integer id, Tag tag) throws SQLException, JsonProcessingException, UpdateException {
        return null;
    }

    @Override
    public Integer deleteTagById(Integer id) throws JsonProcessingException, DeleteException, SQLException {
        return null;
    }
}
