package rs.raf.rafnews.service.implementation;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.repository.implementation.ArticleRepository;
import rs.raf.rafnews.repository.specification.IArticleRepository;
import rs.raf.rafnews.service.specification.IArticleService;

import java.util.List;

@RequestScoped
public class ArticleService implements IArticleService {
    @Inject
    IArticleRepository articleRepository;

    @Override
    public List<Article> getAllArticles() {
        return null;
    }

    @Override
    public Article getArticleById(Integer id) {
        return null;
    }

    @Override
    public Article addArticle(Article article) {
        return null;
    }

    @Override
    public Article updateArticle(Article article) {
        return null;
    }

    @Override
    public boolean deleteArticleById(Integer id) {
        return false;
    }
}
