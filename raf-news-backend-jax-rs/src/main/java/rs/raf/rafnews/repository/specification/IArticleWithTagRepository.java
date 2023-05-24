package rs.raf.rafnews.repository.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.DeleteException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.request.ArticleWithTagRequest;

import java.sql.SQLException;
import java.util.List;

public interface IArticleWithTagRepository {
    List<ArticleWithTag> getAllArticlesWithTag();
    List<ArticleWithTag> getAllArticlesWithTagByArticleId(Integer tagId);
    List<ArticleWithTag> getAllArticlesWithTagByTagId(Integer tagId);
    ArticleWithTag getArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws JsonProcessingException, GetException, SQLException;
    List<ArticleWithTag> addTagListToArticle(List<ArticleWithTagRequest> articleWithTagRequestList) throws JsonProcessingException, AddException;
    ArticleWithTag addTagToArticle(ArticleWithTagRequest articleWithTagRequest) throws GetException, JoinException, JsonProcessingException, AddException, SQLException;
    ArticleWithTag addArticleWithTag(ArticleWithTag articleWithTag) throws JsonProcessingException, AddException, SQLException;
    Integer updateArticleWithTagById(ArticleWithTag articleWithTag);
    Integer updateArticleWithTagByArticleId(ArticleWithTag articleWithTag);
    Integer updateArticleWithTagByTagId(ArticleWithTag articleWithTag);
    Integer deleteArticleWithTagById(Integer id) throws SQLException, JsonProcessingException, DeleteException;
    Integer deleteArticleWithTagByArticleId(Integer id);
    Integer deleteArticleWithTagByTagId(Integer id);
}