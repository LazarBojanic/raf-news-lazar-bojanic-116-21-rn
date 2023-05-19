package rs.raf.rafnews.repository.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.*;

import java.util.List;

public interface IArticleRepository {
    List<Article> getAllRawArticles() throws JsonProcessingException, GetException;
    List<ArticleDto> getAllArticles() throws GetException, JsonProcessingException, JoinException;
    ArticleDto joinArticle(Article article) throws JsonProcessingException, JoinException;
    Article getRawArticleById(Integer id) throws JsonProcessingException, GetException;
    ArticleDto getArticleById(Integer id) throws GetException, JsonProcessingException, JoinException;
    Article addRawArticle(Article article) throws JsonProcessingException, AddException, GetException;
    ArticleDto addArticle(Article article) throws AddException, JsonProcessingException, GetException, JoinException;
    Integer updateArticleById(Integer id, Article article);
    Integer deleteArticleById(Integer id);
}