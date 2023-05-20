package rs.raf.rafnews.repository.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.TagDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Tag;

import java.sql.SQLException;
import java.util.List;

public interface ITagRepository {
    List<Tag> getAllRawTags() throws JsonProcessingException, GetException, SQLException;
    List<TagDto> getAllTags() throws GetException, JsonProcessingException, SQLException;
    List<Tag> getAllRawTagsByArticleId(Integer articleId) throws JsonProcessingException, GetException, SQLException;
    List<TagDto> getAllTagsByArticleId(Integer articleId) throws GetException, JsonProcessingException, SQLException;
    TagDto joinTag(Tag tag);
    Tag getRawTagById(Integer id) throws JsonProcessingException, GetException, SQLException;
    TagDto getTagById(Integer id) throws GetException, JsonProcessingException, SQLException;
    Tag getRawTagByTagName(String tagName) throws JsonProcessingException, GetException, SQLException;
    TagDto getTagByTagName(String tagName) throws GetException, JsonProcessingException, SQLException;
    Tag addRawTag(Tag tag) throws JsonProcessingException, AddException, SQLException;
    TagDto addTag(Tag tag) throws AddException, JsonProcessingException, SQLException;
    List<TagDto> addTagList(List<Tag> tagList) throws AddException, JsonProcessingException, SQLException;
    Integer updateTagById(Integer id, Tag tag);
    Integer deleteTagsById(Integer id);
}