package rs.raf.rafnews.repository.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.TagDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Tag;

import java.util.List;

public interface ITagRepository {
    List<Tag> getAllRawTags() throws JsonProcessingException, GetException;
    List<TagDto> getAllTags() throws GetException, JsonProcessingException;
    List<Tag> getAllRawTagsByArticleId(Integer articleId) throws JsonProcessingException, GetException;
    List<TagDto> getAllTagsByArticleId(Integer articleId) throws GetException, JsonProcessingException;
    TagDto joinTag(Tag tag);
    Tag getRawTagById(Integer id) throws JsonProcessingException, GetException;
    TagDto getTagById(Integer id) throws GetException, JsonProcessingException;
    Tag getRawTagByTagName(String tagName) throws JsonProcessingException, GetException;
    TagDto getTagByTagName(String tagName) throws GetException, JsonProcessingException;
    Tag addRawTag(Tag tag) throws JsonProcessingException, AddException;
    TagDto addTag(Tag tag) throws AddException, JsonProcessingException;
    List<TagDto> addTagList(List<Tag> tagList) throws AddException, JsonProcessingException;
    Integer updateTagById(Integer id, Tag tag);
    Integer deleteTagsById(Integer id);
}