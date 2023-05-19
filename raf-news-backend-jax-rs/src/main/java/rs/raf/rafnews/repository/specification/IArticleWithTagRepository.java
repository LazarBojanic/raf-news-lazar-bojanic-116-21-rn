package rs.raf.rafnews.repository.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.ArticleWithTagRequest;

import java.util.List;

public interface IArticleWithTagRepository {
    List<ArticleWithTag> getAllArticlesWithTag();
    List<ArticleWithTag> getAllArticlesWithTagByArticleId(Integer tagId);
    List<ArticleWithTag> getAllArticlesWithTagByTagId(Integer tagId);
    ArticleWithTag getArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws JsonProcessingException, GetException;
    List<ArticleWithTag> addTagListToArticle(List<ArticleWithTagRequest> articleWithTagRequestList) throws JsonProcessingException, AddException;
    ArticleWithTag addTagToArticle(ArticleWithTagRequest articleWithTagRequest) throws GetException, JoinException, JsonProcessingException, AddException;
    ArticleWithTag addArticleWithTag(ArticleWithTag articleWithTag) throws JsonProcessingException, AddException;
    Integer updateArticleWithTagById(ArticleWithTag articleWithTag);
    Integer updateArticleWithTagByArticleId(ArticleWithTag articleWithTag);
    Integer updateArticleWithTagByTagId(ArticleWithTag articleWithTag);
    Integer deleteArticleWithTagById(Integer id);
    Integer deleteArticleWithTagByArticleId(Integer id);
    Integer deleteArticleWithTagByTagId(Integer id);
}