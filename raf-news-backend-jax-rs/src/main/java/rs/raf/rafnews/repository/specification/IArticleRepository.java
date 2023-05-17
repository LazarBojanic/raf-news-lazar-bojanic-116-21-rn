package rs.raf.rafnews.repository.specification;

import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.model.*;

import java.util.List;

public interface IArticleRepository {
    List<ArticleDto> getAllArticles();
    ArticleDto getArticleById(Integer id);
    ArticleDto addArticle(Article article);
    ArticleDto updateArticle(Article article);
    boolean deleteArticleById(Integer id);
}