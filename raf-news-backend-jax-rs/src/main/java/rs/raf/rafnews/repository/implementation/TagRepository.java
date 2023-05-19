package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.TagDto;
import rs.raf.rafnews.exception.ExceptionMessage;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Category;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.repository.specification.ITagRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class TagRepository implements ITagRepository {
    @Override
    public List<Tag> getAllRawTags() throws JsonProcessingException, GetException {
        List<Tag> tagList = new ArrayList<>();
        String query = "SELECT * FROM tag";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Tag tag = extractTagFromResultSet(resultSet);
                    tagList.add(tag);
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        return tagList;
    }

    @Override
    public List<TagDto> getAllTags() throws GetException, JsonProcessingException {
        List<Tag> tagList = getAllRawTags();
        List<TagDto> tagDtoList = new ArrayList<>();
        for(Tag tag : tagList){
            tagDtoList.add(joinTag(tag));
        }
        return tagDtoList;
    }

    @Override
    public List<Tag> getAllRawTagsByArticleId(Integer articleId) throws JsonProcessingException, GetException {
        List<Tag> tagList = new ArrayList<>();
        String query = "SELECT * FROM article_with_tag WHERE article_id = ?";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, articleId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Integer columnTagId = resultSet.getInt("tag_id");
                    Tag tag = getRawTagById(columnTagId);
                    if(tag.getId() > 0){
                        tagList.add(tag);
                    }
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        return tagList;
    }

    @Override
    public List<TagDto> getAllTagsByArticleId(Integer articleId) throws GetException, JsonProcessingException {
        List<Tag> tagList = getAllRawTagsByArticleId(articleId);
        List<TagDto> tagDtoList = new ArrayList<>();
        for(Tag tag : tagList){
            tagDtoList.add(joinTag(tag));
        }
        return tagDtoList;
    }

    @Override
    public TagDto joinTag(Tag tag) {
        return new TagDto(tag);
    }

    @Override
    public Tag getRawTagById(Integer id) throws JsonProcessingException, GetException {
        String query = "SELECT * FROM tag WHERE id = ?";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return extractTagFromResultSet(resultSet);
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        return new Tag();
    }

    @Override
    public TagDto getTagById(Integer id) throws GetException, JsonProcessingException {
        Tag tag = getRawTagById(id);
        return joinTag(tag);
    }

    @Override
    public Tag getRawTagByTagName(String tagName) throws JsonProcessingException, GetException {
        String query = "SELECT * FROM tag WHERE tag_name = ?";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setString(1, tagName);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return extractTagFromResultSet(resultSet);
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        return new Tag();
    }

    @Override
    public TagDto addTag(Tag tag) {
        return null;
    }

    @Override
    public Integer updateTagById(Integer id, Tag tag) {
        return null;
    }

    @Override
    public Integer deleteTagsById(Integer id) {
        return null;
    }

    private Tag extractTagFromResultSet(ResultSet resultSet) throws SQLException {
        Integer columnId = resultSet.getInt("id");
        String columnTagName = resultSet.getString("tag_name");
        return new Tag(columnId, columnTagName);
    }
}
