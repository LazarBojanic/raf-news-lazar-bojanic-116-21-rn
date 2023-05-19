package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.TagDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.ExceptionMessage;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.Category;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.repository.specification.ITagRepository;
import rs.raf.rafnews.service.specification.IArticleWithTagService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class TagRepository implements ITagRepository {
    @Inject
    private IArticleWithTagService articleWithTagService;
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
    public TagDto getTagByTagName(String tagName) throws GetException, JsonProcessingException {
        Tag tag = getRawTagByTagName(tagName);
        return joinTag(tag);
    }

    @Override
    public Tag addRawTag(Tag tag) throws JsonProcessingException, AddException {
        String query = "INSERT INTO tag(tag_name) VALUES(?)";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, tag.getTag_name());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt("id");
                        return new Tag(id, tag.getTag_name());
                    }
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", e.getMessage());
            throw new AddException(exceptionMessage);
        }
        return new Tag();
    }

    @Override
    public TagDto addTag(Tag tag) throws AddException, JsonProcessingException {
        Tag addedRawTag = addRawTag(tag);
        return joinTag(addedRawTag);
    }

    @Override
    public List<TagDto> addTagList(List<Tag> tagList) throws AddException, JsonProcessingException {
        List<TagDto> tagDtoList = new ArrayList<>();
        for(Tag tag : tagList){
            tagDtoList.add(addTag(tag));
        }
        return tagDtoList;
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
