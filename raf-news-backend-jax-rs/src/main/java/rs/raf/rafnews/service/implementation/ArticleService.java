package rs.raf.rafnews.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.request.ArticleRequest;
import rs.raf.rafnews.request.ArticleSearchRequest;
import rs.raf.rafnews.repository.specification.IArticleRepository;
import rs.raf.rafnews.service.specification.IArticleService;

import java.sql.SQLException;
import java.util.List;

@RequestScoped
public class ArticleService implements IArticleService {
    @Inject
    IArticleRepository articleRepository;

    @Override
    public List<Article> getAllRawArticles() throws GetException, JsonProcessingException, SQLException {
        return this.articleRepository.getAllRawArticles();
    }

    @Override
    public List<ArticleDto> getAllArticles() throws GetException, JoinException, JsonProcessingException, SQLException {
        return this.articleRepository.getAllArticles();
    }

    @Override
    public List<ArticleDto> getAllArticlesFiltered(ArticleSearchRequest articleSearchRequest) throws GetException, JoinException, SQLException, JsonProcessingException {
        return this.articleRepository.getAllArticlesFiltered(articleSearchRequest);
    }

    @Override
    public ArticleDto joinArticle(Article article) throws JoinException, JsonProcessingException {
        return this.articleRepository.joinArticle(article);
    }

    @Override
    public Article getRawArticleById(Integer id) throws GetException, JsonProcessingException, SQLException {
        return this.articleRepository.getRawArticleById(id);
    }

    @Override
    public Article getRawArticleByCategoryId(Integer categoryId) throws GetException, JsonProcessingException, SQLException {
        return this.articleRepository.getRawArticleByCategoryId(categoryId);
    }

    @Override
    public ArticleDto getArticleById(Integer id) throws GetException, JoinException, JsonProcessingException, SQLException {
        return this.articleRepository.getArticleById(id);
    }

    @Override
    public Article addRawArticle(Article article) throws AddException, JsonProcessingException, GetException, SQLException {
        return this.articleRepository.addRawArticle(article);
    }

    @Override
    public ArticleDto addArticle(ArticleRequest articleRequest) throws GetException, JoinException, AddException, JsonProcessingException, SQLException {
        return this.articleRepository.addArticle(articleRequest);
    }

    @Override
    public Integer updateArticleById(Integer id, ArticleRequest articleRequest) throws UpdateException, JsonProcessingException {
        return this.articleRepository.updateArticleById(id, articleRequest);
    }

    @Override
    public Integer saveArticle(Article article) throws SQLException, UpdateException, JsonProcessingException {
        return this.articleRepository.saveArticle(article);
    }

    @Override
    public Integer deleteArticleById(Integer id) throws SQLException, DeleteException, JsonProcessingException {
        return this.articleRepository.deleteArticleById(id);
    }

    @Override
    public Integer deleteAllArticlesByServiceUserId(Integer serviceUserId) throws SQLException, DeleteException, JsonProcessingException {
        return this.articleRepository.deleteAllArticlesByServiceUserId(serviceUserId);
    }
}
