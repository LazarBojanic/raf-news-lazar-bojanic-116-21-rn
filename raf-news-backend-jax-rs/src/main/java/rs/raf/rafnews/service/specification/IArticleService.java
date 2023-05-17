package rs.raf.rafnews.service.specification;

import rs.raf.rafnews.model.Article;

import java.util.List;

public interface IArticleService {
    List<Article> getAllArticles();
    Article getArticleById(Integer id);
    Article addArticle(Article article);
    Article updateArticle(Article article);
    boolean deleteArticleById(Integer id);
}