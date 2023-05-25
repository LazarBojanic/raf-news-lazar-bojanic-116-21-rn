package rs.raf.rafnews.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.ArticleWithTagDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.repository.specification.IArticleWithTagRepository;
import rs.raf.rafnews.service.specification.IArticleWithTagService;

import java.sql.SQLException;
import java.util.List;
@RequestScoped
public class ArticleWithTagService implements IArticleWithTagService {
    @Inject
    IArticleWithTagRepository articleWithTagRepository;

    @Override
    public List<ArticleWithTag> getAllRawArticlesWithTagByByArticleId(Integer articleId) throws SQLException, JsonProcessingException, GetException {
        return this.articleWithTagRepository.getAllRawArticlesWithTagByByArticleId(articleId);
    }

    @Override
    public ArticleWithTag getRawArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws GetException, JsonProcessingException, SQLException {
        return this.articleWithTagRepository.getRawArticleWithTagByArticleIdAndTagId(articleId,tagId);
    }

    @Override
    public ArticleWithTag addRawTagToArticle(Integer articleId, Tag tag) throws GetException, SQLException, AddException, JsonProcessingException {
        return this.articleWithTagRepository.addRawTagToArticle(articleId, tag);
    }

    @Override
    public List<ArticleWithTag> addRawTagListToArticle(Integer articleId, List<Tag> tagList) throws GetException, SQLException, AddException, JsonProcessingException {
        return this.articleWithTagRepository.addRawTagListToArticle(articleId, tagList);
    }

    @Override
    public List<ArticleWithTagDto> getAllArticlesWithTagByByArticleId(Integer articleId) throws SQLException, JsonProcessingException, GetException {
        return this.articleWithTagRepository.getAllArticlesWithTagByByArticleId(articleId);
    }

    @Override
    public ArticleWithTagDto getArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws GetException, JsonProcessingException, SQLException {
        return this.articleWithTagRepository.getArticleWithTagByArticleIdAndTagId(articleId, tagId);
    }

    @Override
    public ArticleWithTagDto addTagToArticle(Integer articleId, Tag tag) throws GetException, SQLException, AddException, JsonProcessingException {
        return this.articleWithTagRepository.addTagToArticle(articleId, tag);
    }

    @Override
    public List<ArticleWithTagDto> addTagListToArticle(Integer articleId, List<Tag> tagList) throws GetException, SQLException, AddException, JsonProcessingException {
        return this.articleWithTagRepository.addTagListToArticle(articleId, tagList);
    }

    @Override
    public ArticleWithTagDto joinArticleWithTag(ArticleWithTag articleWithTag) throws GetException, SQLException, JsonProcessingException {
        return this.articleWithTagRepository.joinArticleWithTag(articleWithTag);
    }
}
