package rs.raf.rafnews.service.implementation;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.repository.specification.IArticleRepository;
import rs.raf.rafnews.service.specification.IArticleService;

import java.util.List;

@RequestScoped
public class ArticleService implements IArticleService {
    @Inject
    IArticleRepository articleRepository;

    @Override
    public List<ArticleDto> getAllArticles() {
        return this.articleRepository.getAllArticles();
    }

    @Override
    public ArticleDto getArticleById(Integer id) {
        return this.articleRepository.getArticleById(id);
    }

    @Override
    public ArticleDto addArticle(Article article) {
        return this.articleRepository.addArticle(article);
    }

    @Override
    public ArticleDto updateArticle(Article article) {
        return this.articleRepository.updateArticle(article);
    }

    @Override
    public boolean deleteArticleById(Integer id) {
        return this.articleRepository.deleteArticleById(id);
    }
}
