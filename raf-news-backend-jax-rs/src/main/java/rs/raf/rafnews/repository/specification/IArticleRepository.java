package rs.raf.rafnews.repository.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.DeleteException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.request.ArticleRequest;
import rs.raf.rafnews.request.ArticleSearchRequest;

import java.sql.SQLException;
import java.util.List;

public interface IArticleRepository {
    List<Article> getAllRawArticles() throws JsonProcessingException, GetException, SQLException;
    List<ArticleDto> getAllArticles() throws GetException, JsonProcessingException, JoinException, SQLException;
    List<ArticleDto> getAllArticlesFiltered(ArticleSearchRequest articleSearchRequest) throws GetException, JoinException, SQLException, JsonProcessingException;
    ArticleDto joinArticle(Article article) throws JsonProcessingException, JoinException;

    Article getRawArticleById(Integer id) throws JsonProcessingException, GetException, SQLException;
    ArticleDto getArticleById(Integer id) throws GetException, JsonProcessingException, JoinException, SQLException;
    Article addRawArticle(Article article) throws JsonProcessingException, AddException, GetException, SQLException;
    ArticleDto addArticle(ArticleRequest articleRequest) throws AddException, JsonProcessingException, GetException, JoinException, SQLException;
    Integer updateArticleById(Integer id, ArticleRequest articleRequest);
    Integer deleteArticleById(Integer id) throws JsonProcessingException, DeleteException, SQLException;
}