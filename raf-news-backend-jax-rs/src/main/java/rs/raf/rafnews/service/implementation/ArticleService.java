package rs.raf.rafnews.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.model.Category;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.repository.specification.IArticleRepository;
import rs.raf.rafnews.service.specification.IArticleService;

import java.util.List;

@RequestScoped
public class ArticleService implements IArticleService {
    @Inject
    IArticleRepository articleRepository;

    @Override
    public List<Article> getAllRawArticles() throws GetException, JsonProcessingException {
        return this.articleRepository.getAllRawArticles();
    }

    @Override
    public List<ArticleDto> getAllArticles() throws GetException, JoinException, JsonProcessingException {
        return this.articleRepository.getAllArticles();
    }

    @Override
    public ArticleDto joinArticle(Article article) throws JoinException, JsonProcessingException {
        return this.articleRepository.joinArticle(article);
    }

    @Override
    public Article getRawArticleById(Integer id) throws GetException, JsonProcessingException {
        return this.articleRepository.getRawArticleById(id);
    }

    @Override
    public ArticleDto getArticleById(Integer id) throws GetException, JoinException, JsonProcessingException {
        return this.articleRepository.getArticleById(id);
    }

    @Override
    public Article addRawArticle(Article article) throws AddException, JsonProcessingException {
        return this.articleRepository.addRawArticle(article);
    }

    @Override
    public ArticleDto addArticle(ArticleDto articleDto) throws GetException, JoinException, AddException, JsonProcessingException {
        return this.articleRepository.addArticle(articleDto);
    }

    @Override
    public Integer updateArticleById(Integer id, Article article) {
        return this.articleRepository.updateArticleById(id, article);
    }

    @Override
    public Integer deleteArticleById(Integer id) {
        return this.articleRepository.deleteArticleById(id);
    }
}
