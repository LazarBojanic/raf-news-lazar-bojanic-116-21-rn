package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.ArticleWithTagDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.DeleteException;
import rs.raf.rafnews.exception.ExceptionMessage;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.repository.specification.IArticleWithTagRepository;
import rs.raf.rafnews.service.specification.ITagService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@RequestScoped
public class ArticleWithTagRepository implements IArticleWithTagRepository {
    @Inject
    private ITagService tagService;
    @Override
    public List<ArticleWithTag> getAllRawArticlesWithTagByByArticleId(Integer articleId) throws SQLException, JsonProcessingException, GetException {
        List<ArticleWithTag> articleWithTagList = new ArrayList<>();
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        try{
            String query = "SELECT * FROM article_with_tag WHERE article_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, articleId);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()){
                        ArticleWithTag articleWithTag = extractArticleWithTagFromResultSet(resultSet);
                        articleWithTagList.add(articleWithTag);
                    }
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        finally {
            connection.close();
        }
        return articleWithTagList;
    }

    @Override
    public ArticleWithTag getRawArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws GetException, JsonProcessingException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        try{
            String query = "SELECT * FROM article_with_tag WHERE article_id = ? AND tag_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, articleId);
                preparedStatement.setInt(2, tagId);
                preparedStatement.executeUpdate();
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt("id");
                        return new ArticleWithTag(id, articleId, tagId);
                    }
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        finally {
            connection.close();
        }
        return new ArticleWithTag();
    }

    @Override
    public ArticleWithTag addRawTagToArticle(Integer articleId, Tag tag) throws GetException, SQLException, JsonProcessingException, AddException {
        if(getArticleWithTagByArticleIdAndTagId(articleId, tagService.getTagByTagName(tag.getTag_name()).getId()).getId() <= 0){
            Connection connection = RafNewsDatabase.getInstance().getConnection();
            try{
                String query = "INSERT INTO article_with_tag(article_id, tag_id) VALUES(?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setInt(1, articleId);
                    preparedStatement.setInt(2, tag.getId());
                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int id = generatedKeys.getInt("id");
                                return new ArticleWithTag(id, articleId, tag.getId());
                            }
                        }
                    }
                }
            }
            catch (SQLException e) {
                ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", e.getMessage());
                throw new AddException(exceptionMessage);
            }
            finally {
                connection.close();
            }
        }
        return new ArticleWithTag();

    }

    @Override
    public List<ArticleWithTag> addRawTagListToArticle(Integer articleId, List<Tag> tagList) throws GetException, SQLException, AddException, JsonProcessingException {
        List<ArticleWithTag> articleWithTagList = new ArrayList<>();
        for(Tag tag : tagList){
            ArticleWithTag addedArticleWithTag = addRawTagToArticle(articleId, tag);
            if(addedArticleWithTag.getId() > 0){
                articleWithTagList.add(addedArticleWithTag);
            }
        }
        return articleWithTagList;
    }


    @Override
    public List<ArticleWithTagDto> getAllArticlesWithTagByByArticleId(Integer articleId) throws SQLException, JsonProcessingException, GetException {
        List<ArticleWithTagDto> articleWithTagDtoList = new ArrayList<>();
        List<ArticleWithTag> rawArticleWithTagList = getAllRawArticlesWithTagByByArticleId(articleId);
        for(ArticleWithTag articleWithTag : rawArticleWithTagList){
            articleWithTagDtoList.add(joinArticleWithTag(articleWithTag));
        }
        return articleWithTagDtoList;
    }

    @Override
    public ArticleWithTagDto getArticleWithTagByArticleIdAndTagId(Integer articleId, Integer tagId) throws GetException, JsonProcessingException, SQLException {
        return joinArticleWithTag(getRawArticleWithTagByArticleIdAndTagId(articleId, tagId));
    }

    @Override
    public ArticleWithTagDto addTagToArticle(Integer articleId, Tag tag) throws GetException, SQLException, AddException, JsonProcessingException {
        return joinArticleWithTag(addRawTagToArticle(articleId, tag));
    }

    @Override
    public List<ArticleWithTagDto> addTagListToArticle(Integer articleId, List<Tag> tagList) throws GetException, SQLException, AddException, JsonProcessingException {
        List<ArticleWithTagDto> articleWithTagDtoList = new ArrayList<>();
        List<ArticleWithTag> rawArticleWithTagList = addRawTagListToArticle(articleId, tagList);
        for(ArticleWithTag articleWithTag : rawArticleWithTagList){
            articleWithTagDtoList.add(joinArticleWithTag(articleWithTag));
        }
        return articleWithTagDtoList;
    }

    @Override
    public ArticleWithTagDto joinArticleWithTag(ArticleWithTag articleWithTag) throws GetException, SQLException, JsonProcessingException {
        Tag tag = tagService.getTagById(articleWithTag.getTag_id());
        return new ArticleWithTagDto(articleWithTag.getId(), articleWithTag.getArticle_id(), tag);
    }

    @Override
    public List<ArticleWithTagDto> updateTagsForArticle(Integer articleId, List<Tag> tagList) throws GetException, SQLException, JsonProcessingException, DeleteException, AddException {
        deleteAllTagsForArticle(articleId);
        return addTagListToArticle(articleId, tagList);
    }

    @Override
    public Integer deleteAllTagsForArticle(Integer articleId) throws SQLException, JsonProcessingException, DeleteException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        try{
            String query = "DELETE FROM article_with_tag WHERE article_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, articleId);
                return preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("DeleteException", e.getMessage());
            throw new DeleteException(exceptionMessage);
        }
        finally {
            connection.close();
        }
    }

    private ArticleWithTag extractArticleWithTagFromResultSet(ResultSet resultSet) throws SQLException {
        Integer columnId = resultSet.getInt("id");
        Integer columnArticleId = resultSet.getInt("article_id");
        Integer columnTagId = resultSet.getInt("tag_id");
        return new ArticleWithTag(columnId, columnArticleId, columnTagId);
    }
}
