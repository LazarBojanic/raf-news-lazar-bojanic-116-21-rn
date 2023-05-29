package rs.raf.rafnews.service.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.request.ArticleRequest;
import rs.raf.rafnews.request.ArticleSearchRequest;

import java.sql.SQLException;
import java.util.List;

public interface IArticleService {
    List<Article> getAllRawArticles() throws GetException, JsonProcessingException, SQLException;
    List<ArticleDto> getAllArticles() throws GetException, JoinException, JsonProcessingException, SQLException;
    List<ArticleDto> getAllArticlesFiltered(ArticleSearchRequest articleSearchRequest) throws GetException, JoinException, SQLException, JsonProcessingException;
    ArticleDto joinArticle(Article article) throws JoinException, JsonProcessingException;
    Article getRawArticleById(Integer id) throws GetException, JsonProcessingException, SQLException;
    Article getRawArticleByCategoryId(Integer categoryId) throws GetException, JsonProcessingException, SQLException;
    ArticleDto getArticleById(Integer id) throws GetException, JoinException, JsonProcessingException, SQLException;
    Article addRawArticle(Article article) throws AddException, JsonProcessingException, GetException, SQLException;
    ArticleDto addArticle(ArticleRequest articleRequest) throws GetException, JoinException, AddException, JsonProcessingException, SQLException;
    Integer updateArticleById(Integer id, ArticleRequest articleRequest) throws UpdateException, JsonProcessingException;
    Integer saveArticle(Article article) throws SQLException, UpdateException, JsonProcessingException;
    Integer deleteArticleById(Integer id) throws SQLException, DeleteException, JsonProcessingException;
}