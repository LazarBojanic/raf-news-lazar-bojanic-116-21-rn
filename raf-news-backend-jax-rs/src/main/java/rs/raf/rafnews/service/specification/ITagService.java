package rs.raf.rafnews.service.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.dto.TagDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.model.Tag;

import java.sql.SQLException;
import java.util.List;

public interface ITagService {
    List<Tag> getAllRawTags() throws GetException, JsonProcessingException, SQLException;
    List<TagDto> getAllTags() throws GetException, JsonProcessingException, SQLException;
    List<Tag> getAllRawTagsByArticleId(Integer articleId) throws GetException, JsonProcessingException, SQLException;
    List<TagDto> getAllTagsByArticleId(Integer articleId) throws GetException, JsonProcessingException, SQLException;
    TagDto joinTag(Tag tag);
    Tag getRawTagById(Integer id) throws GetException, JsonProcessingException, SQLException;
    TagDto getTagById(Integer id) throws GetException, JsonProcessingException, SQLException;
    Tag getRawTagByTagName(String tagName) throws GetException, JsonProcessingException, SQLException;
    TagDto getTagByTagName(String tagName) throws GetException, JsonProcessingException, SQLException;
    Tag addRawTag(Tag tag) throws AddException, JsonProcessingException, SQLException;
    TagDto addTag(Tag tag) throws AddException, JsonProcessingException, SQLException;
    List<TagDto> addTagList(List<Tag> tagList) throws AddException, JsonProcessingException, SQLException;
    Integer updateTagById(Integer id, Tag tag);
    Integer deleteTagById(Integer id);
}