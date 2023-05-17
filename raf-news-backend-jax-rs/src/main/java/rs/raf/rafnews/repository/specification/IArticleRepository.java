package rs.raf.rafnews.repository.specification;

import rs.raf.rafnews.model.*;

import java.util.List;

public interface IArticleRepository {
    List<Article> getAllArticles();
    Article getArticleById(Integer id);
    Article addArticle(Article article);
    Article updateArticle(Article article);
    boolean deleteArticleById(Integer id);
}