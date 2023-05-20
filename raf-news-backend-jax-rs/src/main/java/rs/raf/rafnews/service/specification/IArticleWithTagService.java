package rs.raf.rafnews.service.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.TagDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.ArticleWithTagRequest;
import rs.raf.rafnews.model.Tag;

import java.sql.SQLException;
import java.util.List;

public interface IArticleWithTagService {
    List<ArticleWithTag> getAllArticlesWithTag();
    List<ArticleWithTag> getAllArticlesWithTagByArticleId(Integer tagId);
    List<ArticleWithTag> getAllArticlesWithTagByTagId(Integer tagId);
    ArticleWithTag getArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws GetException, JsonProcessingException, SQLException;
    List<ArticleWithTag> addTagListToArticle(List<ArticleWithTagRequest> articleWithTagRequestList) throws AddException, JsonProcessingException;
    ArticleWithTag addTagToArticle(ArticleWithTagRequest articleWithTagRequest) throws GetException, JoinException, AddException, JsonProcessingException, SQLException;
    ArticleWithTag addArticleWithTag(ArticleWithTag articleWithTag) throws AddException, JsonProcessingException, SQLException;
    Integer updateArticleWithTagById(ArticleWithTag articleWithTag);
    Integer updateArticleWithTagByArticleId(ArticleWithTag articleWithTag);
    Integer updateArticleWithTagByTagId(ArticleWithTag articleWithTag);
    Integer deleteArticleWithTagById(Integer id);
    Integer deleteArticleWithTagByArticleId(Integer id);
    Integer deleteArticleWithTagByTagId(Integer id);
}