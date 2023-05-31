package rs.raf.rafnews.repository.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.DeleteException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.UpdateException;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.Tag;

import java.sql.SQLException;
import java.util.List;

public interface ITagRepository {
    List<Tag> getAllTags() throws GetException, JsonProcessingException, SQLException;
    Tag getTagById(Integer id) throws JsonProcessingException, GetException, SQLException;
    Tag getTagByTagName(String tagName) throws GetException, JsonProcessingException, SQLException;
    List<Tag> getTagListByTagNameList(List<String> tagNameList) throws GetException, JsonProcessingException, SQLException;
    Tag addTag(String tagName) throws SQLException, AddException, JsonProcessingException, GetException;
    List<Tag> addTagList(List<String> tagNameList) throws SQLException, AddException, JsonProcessingException, GetException;
    Integer updateTagById(Integer id, String tagName) throws SQLException, JsonProcessingException, UpdateException;
    Integer deleteTagById(Integer id) throws JsonProcessingException, DeleteException, SQLException;
}
