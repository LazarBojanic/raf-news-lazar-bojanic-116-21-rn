package rs.raf.rafnews.service.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.ArticleWithTagDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.Tag;

import java.sql.SQLException;
import java.util.List;

public interface IArticleWithTagService {

    List<ArticleWithTag> getAllRawArticlesWithTagByByArticleId(Integer articleId) throws SQLException, JsonProcessingException, GetException;
    ArticleWithTag getRawArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws GetException, JsonProcessingException, SQLException;
    ArticleWithTag addRawTagToArticle(Integer articleId, Tag tag) throws GetException, SQLException, AddException, JsonProcessingException;
    List<ArticleWithTag> addRawTagListToArticle(Integer articleId, List<Tag> tagList) throws GetException, SQLException, AddException, JsonProcessingException;





    List<ArticleWithTagDto> getAllArticlesWithTagByByArticleId(Integer articleId) throws SQLException, JsonProcessingException, GetException;
    ArticleWithTagDto getArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws GetException, JsonProcessingException, SQLException;
    ArticleWithTagDto addTagToArticle(Integer articleId, Tag tag) throws GetException, SQLException, AddException, JsonProcessingException;
    List<ArticleWithTagDto> addTagListToArticle(Integer articleId, List<Tag> tagList) throws GetException, SQLException, AddException, JsonProcessingException;


    ArticleWithTagDto joinArticleWithTag(ArticleWithTag articleWithTag) throws GetException, SQLException, JsonProcessingException;

    ArticleWithTagDto updateTagsForArticle(Integer articleId, List<Tag> tagList) throws GetException, SQLException, JsonProcessingException;
    Integer deleteAllTagsForArticle(Integer articleId);
}
