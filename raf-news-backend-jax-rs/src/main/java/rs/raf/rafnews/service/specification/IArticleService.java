package rs.raf.rafnews.service.specification;

import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.model.Article;

import java.util.List;

public interface IArticleService {
    List<ArticleDto> getAllArticles();
    ArticleDto getArticleById(Integer id);
    ArticleDto addArticle(Article article);
    ArticleDto updateArticle(Article article);
    boolean deleteArticleById(Integer id);
}