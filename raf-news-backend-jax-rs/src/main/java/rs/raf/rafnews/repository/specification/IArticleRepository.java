package rs.raf.rafnews.repository.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.*;

import java.sql.SQLException;
import java.util.List;

public interface IArticleRepository {
    List<Article> getAllRawArticles() throws JsonProcessingException, GetException, SQLException;
    List<ArticleDto> getAllArticles() throws GetException, JsonProcessingException, JoinException, SQLException;
    List<ArticleDto> getAllArticlesFiltered(ArticleSearchParams articleSearchParams) throws GetException, JoinException, SQLException, JsonProcessingException;
    ArticleDto joinArticle(Article article) throws JsonProcessingException, JoinException;

    Article getRawArticleById(Integer id) throws JsonProcessingException, GetException, SQLException;
    ArticleDto getArticleById(Integer id) throws GetException, JsonProcessingException, JoinException, SQLException;
    Article addRawArticle(Article article) throws JsonProcessingException, AddException, GetException, SQLException;
    ArticleDto addArticle(Article article) throws AddException, JsonProcessingException, GetException, JoinException, SQLException;
    Integer updateArticleById(Integer id, Article article);
    Integer deleteArticleById(Integer id);
}