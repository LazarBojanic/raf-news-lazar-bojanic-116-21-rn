package rs.raf.rafnews.service.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.dto.TagDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.model.Tag;

import java.util.List;

public interface ITagService {
    List<Tag> getAllRawTags() throws GetException, JsonProcessingException;
    List<TagDto> getAllTags() throws GetException, JsonProcessingException;
    List<Tag> getAllRawTagsByArticleId(Integer articleId) throws GetException, JsonProcessingException;
    List<TagDto> getAllTagsByArticleId(Integer articleId) throws GetException, JsonProcessingException;
    TagDto joinTag(Tag tag);
    Tag getRawTagById(Integer id) throws GetException, JsonProcessingException;
    TagDto getTagById(Integer id) throws GetException, JsonProcessingException;
    Tag getRawTagByTagName(String tagName) throws GetException, JsonProcessingException;
    TagDto getTagByTagName(String tagName) throws GetException, JsonProcessingException;
    Tag addRawTag(Tag tag) throws AddException, JsonProcessingException;
    TagDto addTag(Tag tag) throws AddException, JsonProcessingException;
    List<TagDto> addTagList(List<Tag> tagList) throws AddException, JsonProcessingException;
    Integer updateTagById(Integer id, Tag tag);
    Integer deleteTagById(Integer id);
}