package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.dto.TagDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.ExceptionMessage;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.ArticleWithTagRequest;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.repository.specification.IArticleRepository;
import rs.raf.rafnews.repository.specification.IArticleWithTagRepository;
import rs.raf.rafnews.service.specification.IArticleService;
import rs.raf.rafnews.service.specification.ITagService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@RequestScoped
public class ArticleWithTagRepository implements IArticleWithTagRepository {
    @Inject
    IArticleService articleService;
    @Inject
    ITagService tagService;
    @Override
    public List<ArticleWithTag> getAllArticlesWithTag() {
        return null;
    }

    @Override
    public List<ArticleWithTag> getAllArticlesWithTagByArticleId(Integer tagId) {
        return null;
    }

    @Override
    public List<ArticleWithTag> getAllArticlesWithTagByTagId(Integer tagId) {
        return null;
    }

    @Override
    public ArticleWithTag getArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws JsonProcessingException, GetException {
        String query = "SELECT * FROM article_with_tag WHERE article_id = ? AND tag_id = ?";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, articleId);
            preparedStatement.setInt(2, tagId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Integer columnId = resultSet.getInt("id");
                    Integer columnArticleId = resultSet.getInt("article_id");
                    Integer columnTagId = resultSet.getInt("tag_id");
                    return new ArticleWithTag(columnId, columnArticleId, columnTagId);
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        return new ArticleWithTag();
    }

    @Override
    public List<ArticleWithTag> addTagListToArticle(List<ArticleWithTagRequest> articleWithTagRequestList) throws JsonProcessingException, AddException {
        StringBuilder exceptions = new StringBuilder();
        List<ArticleWithTag> articleWithTagList = new ArrayList<>();
        for(ArticleWithTagRequest articleWithTagRequest : articleWithTagRequestList){
            try{
                articleWithTagList.add(addTagToArticle(articleWithTagRequest));
            }
            catch(Exception e){
                exceptions.append(e.getMessage()).append("\n");
            }
        }
        if(!exceptions.isEmpty()){
            ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", exceptions.toString() + " Successful additions: " + articleWithTagList);
            throw new AddException(exceptionMessage);
        }
        return articleWithTagList;
    }

    @Override
    public ArticleWithTag addTagToArticle(ArticleWithTagRequest articleWithTagRequest) throws GetException, JoinException, JsonProcessingException, AddException {
        ArticleDto articleDto = articleService.getArticleById(articleWithTagRequest.getArticle_id());
        if(articleDto.getId() > 0){
            TagDto existingTag = tagService.getTagByTagName(articleWithTagRequest.getTag_name());
            if(existingTag.getId() > 0){
                ArticleWithTag existingArticleWithTag = getArticleWithTagByArticleIdAndTagId(articleWithTagRequest.getArticle_id(), existingTag.getId());
                if(existingArticleWithTag.getId() <= 0){
                    return addArticleWithTag(new ArticleWithTag(0, articleWithTagRequest.getArticle_id(), existingTag.getId()));
                }
                else{
                    ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", "Failed to add tag to article, it already has the tag: " + articleWithTagRequest.getTag_name());
                    throw new GetException(exceptionMessage);
                }
            }
            else{
                TagDto addedTag = tagService.addTag(new Tag(0, articleWithTagRequest.getTag_name()));
                if(addedTag.getId() > 0){
                    ArticleWithTag existingArticleWithTag = getArticleWithTagByArticleIdAndTagId(articleWithTagRequest.getArticle_id(), addedTag.getId());
                    if(existingArticleWithTag.getId() <= 0){
                        return addArticleWithTag(new ArticleWithTag(0, articleWithTagRequest.getArticle_id(), addedTag.getId()));
                    }
                    else{
                        ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", "Failed to add tag to article, it already has the tag: " + articleWithTagRequest.getTag_name());
                        throw new GetException(exceptionMessage);
                    }
                }
                else{
                    ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", "Failed to add tag to article, it already has the tag.");
                    throw new GetException(exceptionMessage);
                }
            }
        }
        else{
            ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", "Failed to add tag to article. Article with id:  " + articleWithTagRequest.getArticle_id() + " doesn't exist");
            throw new GetException(exceptionMessage);
        }
    }

    @Override
    public ArticleWithTag addArticleWithTag(ArticleWithTag articleWithTag) throws JsonProcessingException, AddException {
        String query = "INSERT INTO article_with_tag(article_id, tag_id) VALUES(?, ?)";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, articleWithTag.getArticle_id());
            preparedStatement.setInt(2, articleWithTag.getTag_id());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt("id");
                        return new ArticleWithTag(id, articleWithTag.getArticle_id(), articleWithTag.getTag_id());
                    }
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", e.getMessage());
            throw new AddException(exceptionMessage);
        }
        return new ArticleWithTag();
    }

    @Override
    public Integer updateArticleWithTagById(ArticleWithTag articleWithTag) {
        return null;
    }

    @Override
    public Integer updateArticleWithTagByArticleId(ArticleWithTag articleWithTag) {
        return null;
    }

    @Override
    public Integer updateArticleWithTagByTagId(ArticleWithTag articleWithTag) {
        return null;
    }

    @Override
    public Integer deleteArticleWithTagById(Integer id) {
        return null;
    }

    @Override
    public Integer deleteArticleWithTagByArticleId(Integer id) {
        return null;
    }

    @Override
    public Integer deleteArticleWithTagByTagId(Integer id) {
        return null;
    }
}
