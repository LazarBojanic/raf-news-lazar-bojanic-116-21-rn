package rs.raf.rafnews.service.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.model.Tag;

import java.sql.SQLException;
import java.util.List;

public interface IArticleService {
    List<Article> getAllRawArticles() throws GetException, JsonProcessingException, SQLException;
    List<ArticleDto> getAllArticles() throws GetException, JoinException, JsonProcessingException, SQLException;
    ArticleDto joinArticle(Article article) throws JoinException, JsonProcessingException;
    Article getRawArticleById(Integer id) throws GetException, JsonProcessingException, SQLException;
    ArticleDto getArticleById(Integer id) throws GetException, JoinException, JsonProcessingException, SQLException;
    Article addRawArticle(Article article) throws AddException, JsonProcessingException, GetException, SQLException;
    ArticleDto addArticle(Article article) throws GetException, JoinException, AddException, JsonProcessingException, SQLException;
    Integer updateArticleById(Integer id, Article article);
    Integer deleteArticleById(Integer id);
}