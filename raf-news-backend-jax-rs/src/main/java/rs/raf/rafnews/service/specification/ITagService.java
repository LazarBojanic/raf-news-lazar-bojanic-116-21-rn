package rs.raf.rafnews.service.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.DeleteException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.UpdateException;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.Tag;

import java.sql.SQLException;
import java.util.List;

public interface ITagService {
    List<Tag> getAllTags() throws GetException, JsonProcessingException, SQLException;
    Tag getTagById(Integer id) throws JsonProcessingException, GetException, SQLException;
    Tag getTagByTagName(String tagName) throws GetException, JsonProcessingException, SQLException;

    Tag addTag(Tag tag) throws SQLException, AddException, JsonProcessingException, GetException;
    List<Tag> addTagList(List<Tag> tagList) throws SQLException, AddException, JsonProcessingException, GetException;

    Integer updateTagById(Integer id, Tag tag) throws SQLException, JsonProcessingException, UpdateException;
    Integer deleteTagById(Integer id) throws JsonProcessingException, DeleteException, SQLException;
}