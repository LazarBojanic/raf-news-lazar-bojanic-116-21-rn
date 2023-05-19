package rs.raf.rafnews.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.ArticleWithTagRequest;
import rs.raf.rafnews.repository.specification.IArticleWithTagRepository;
import rs.raf.rafnews.service.specification.IArticleWithTagService;

import java.util.List;
@RequestScoped
public class ArticleWithTagService implements IArticleWithTagService {
    @Inject
    IArticleWithTagRepository articleWithTagRepository;
    @Override
    public List<ArticleWithTag> getAllArticlesWithTag() {
        return this.articleWithTagRepository.getAllArticlesWithTag();
    }

    @Override
    public List<ArticleWithTag> getAllArticlesWithTagByArticleId(Integer tagId) {
        return this.articleWithTagRepository.getAllArticlesWithTagByArticleId(tagId);
    }

    @Override
    public List<ArticleWithTag> getAllArticlesWithTagByTagId(Integer tagId) {
        return this.articleWithTagRepository.getAllArticlesWithTagByTagId(tagId);
    }

    @Override
    public ArticleWithTag getArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws GetException, JsonProcessingException {
        return this.articleWithTagRepository.getArticleWithTagByArticleIdAndTagId(articleId, tagId);
    }

    @Override
    public ArticleWithTag addTagToArticle(ArticleWithTagRequest articleWithTagRequest) throws GetException, JoinException, AddException, JsonProcessingException {
        return this.articleWithTagRepository.addTagToArticle(articleWithTagRequest);
    }
    @Override
    public List<ArticleWithTag> addTagListToArticle(List<ArticleWithTagRequest> articleWithTagRequestList) throws AddException, JsonProcessingException {
        return this.articleWithTagRepository.addTagListToArticle(articleWithTagRequestList);
    }
    @Override
    public ArticleWithTag addArticleWithTag(ArticleWithTag articleWithTag) throws AddException, JsonProcessingException {
        return this.articleWithTagRepository.addArticleWithTag(articleWithTag);
    }

    @Override
    public Integer updateArticleWithTagById(ArticleWithTag articleWithTag) {
        return this.articleWithTagRepository.updateArticleWithTagById(articleWithTag);
    }

    @Override
    public Integer updateArticleWithTagByArticleId(ArticleWithTag articleWithTag) {
        return this.articleWithTagRepository.updateArticleWithTagByArticleId(articleWithTag);
    }

    @Override
    public Integer updateArticleWithTagByTagId(ArticleWithTag articleWithTag) {
        return this.articleWithTagRepository.updateArticleWithTagByTagId(articleWithTag);
    }

    @Override
    public Integer deleteArticleWithTagById(Integer id) {
        return this.articleWithTagRepository.deleteArticleWithTagById(id);
    }

    @Override
    public Integer deleteArticleWithTagByArticleId(Integer id) {
        return this.articleWithTagRepository.deleteArticleWithTagByArticleId(id);
    }

    @Override
    public Integer deleteArticleWithTagByTagId(Integer id) {
        return this.articleWithTagRepository.deleteArticleWithTagByTagId(id);
    }
}
