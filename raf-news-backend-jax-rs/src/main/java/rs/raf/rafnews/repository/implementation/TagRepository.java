package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.ArticleWithTag;
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
        return null;
    }

    @Override
    public Tag getTagByTagName(String tagName) throws GetException, JsonProcessingException, SQLException {
        return null;
    }


    @Override
    public Tag addTag(Tag tag) throws SQLException, AddException, JsonProcessingException, GetException {
        if(getTagByTagName(tag.getTag_name()).getId() <= 0){
            Connection connection = RafNewsDatabase.getInstance().getConnection();
            try{
                String query = "INSERT INTO tag(tag_name) VALUES(?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, tag.getTag_name());
                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int id = generatedKeys.getInt("id");
                                tag.setId(id);
                                return tag;
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
    public List<Tag> addTagList(List<Tag> tagList) throws SQLException, AddException, JsonProcessingException, GetException {
        List<Tag> addedTagList = new ArrayList<>();
        for(Tag tag : tagList){
            Tag addedTag = addTag(tag);
            if(addedTag.getId() > 0){
                addedTagList.add(addedTag);
            }
        }
        return addedTagList;
    }


    @Override
    public Integer updateTagById(Integer id, Tag tag) throws SQLException, JsonProcessingException, UpdateException {
        return null;
    }

    @Override
    public Integer deleteTagById(Integer id) throws JsonProcessingException, DeleteException, SQLException {
        return null;
    }
}
