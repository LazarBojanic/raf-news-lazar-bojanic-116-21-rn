package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.ArticleWithTag;
import rs.raf.rafnews.model.Category;
import rs.raf.rafnews.model.Tag;
import rs.raf.rafnews.repository.specification.ITagRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@RequestScoped
public class TagRepository implements ITagRepository {
    @Override
    public List<Tag> getAllTags() throws GetException, JsonProcessingException, SQLException {
        return null;
    }

    @Override
    public Tag getTagById(Integer id) throws JsonProcessingException, GetException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "SELECT * FROM tag WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
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
        finally {
            connection.close();
        }
        return new Tag();
    }

    @Override
    public Tag getTagByTagName(String tagName) throws GetException, JsonProcessingException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "SELECT * FROM tag WHERE tag_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
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
        finally {
            connection.close();
        }
        return new Tag();
    }

    @Override
    public List<Tag> getTagListByTagNameList(List<String> tagNameList) throws GetException, JsonProcessingException, SQLException {
        List<Tag> tagList = new ArrayList<>();
        for(String tagName : tagNameList){
            tagList.add(getTagByTagName(tagName));
        }
        return tagList;
    }

    @Override
    public Tag addTag(String tagName) throws SQLException, AddException, JsonProcessingException, GetException {
        if(getTagByTagName(tagName).getId() <= 0){
            Connection connection = RafNewsDatabase.getInstance().getConnection();
            try{
                String query = "INSERT INTO tag(tag_name) VALUES(?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, tagName);
                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int id = generatedKeys.getInt("id");
                                return new Tag(id, tagName);
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
        return new Tag();
    }

    @Override
    public List<Tag> addTagList(List<String> tagNameList) throws SQLException, AddException, JsonProcessingException, GetException {
        List<Tag> addedTagList = new ArrayList<>();
        for(String tagName : tagNameList){
            Tag addedTag = addTag(tagName);
            if(addedTag.getId() > 0){
                addedTagList.add(addedTag);
            }
        }
        return addedTagList;
    }


    @Override
    public Integer updateTagById(Integer id, String tagName) throws SQLException, JsonProcessingException, UpdateException {
        return null;
    }

    @Override
    public Integer deleteTagById(Integer id) throws JsonProcessingException, DeleteException, SQLException {
        return null;
    }
    private Tag extractTagFromResultSet(ResultSet resultSet) throws SQLException {
        Integer columnId = resultSet.getInt("id");
        String columnTagName = resultSet.getString("tag_name");
        return new Tag(columnId, columnTagName);
    }
}
