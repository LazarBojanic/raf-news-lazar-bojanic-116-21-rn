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
        return null;
    }

    @Override
    public ArticleWithTag getRawArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws GetException, JsonProcessingException, SQLException {
        return null;
    }

    @Override
    public ArticleWithTag addRawTagToArticle(Integer articleId, Tag tag) throws GetException, SQLException, AddException, JsonProcessingException {
        return null;
    }

    @Override
    public List<ArticleWithTag> addRawTagListToArticle(Integer articleId, List<Tag> tagList) throws GetException, SQLException, AddException, JsonProcessingException {
        return null;
    }

    @Override
    public List<ArticleWithTagDto> getAllArticlesWithTagByByArticleId(Integer articleId) throws SQLException, JsonProcessingException, GetException {
        return null;
    }

    @Override
    public ArticleWithTagDto getArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws GetException, JsonProcessingException, SQLException {
        return null;
    }

    @Override
    public ArticleWithTagDto addTagToArticle(Integer articleId, Tag tag) throws GetException, SQLException, AddException, JsonProcessingException {
        return null;
    }

    @Override
    public List<ArticleWithTagDto> addTagListToArticle(Integer articleId, List<Tag> tagList) throws GetException, SQLException, AddException, JsonProcessingException {
        return null;
    }

    @Override
    public ArticleWithTagDto joinArticleWithTag(ArticleWithTag articleWithTag) {
        return null;
    }
}
