package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.dto.CommentDto;
import rs.raf.rafnews.exception.ExceptionMessage;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.model.Comment;
import rs.raf.rafnews.repository.specification.ICommentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CommentRepository implements ICommentRepository {
    @Override
    public List<Comment> getAllRawComments() throws GetException, JsonProcessingException {
        List<Comment> commentList = new ArrayList<>();
        String query = "SELECT * FROM comment";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Comment comment = extractCommentFromResultSet(resultSet);
                    commentList.add(comment);
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        return commentList;
    }

    @Override
    public List<CommentDto> getAllComments() throws GetException, JsonProcessingException {
        List<Comment> commentList = getAllRawComments();
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList){
            CommentDto commentDto = joinComment(comment);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }

    @Override
    public List<Comment> getAllRawCommentsByArticleId(Integer articleId) throws GetException, JsonProcessingException {
        return null;
    }

    @Override
    public List<CommentDto> getAllCommentsByArticleId(Integer articleId) throws GetException, JsonProcessingException {
        return null;
    }

    @Override
    public CommentDto joinComment(Comment comment) {
        return null;
    }

    @Override
    public Comment getRawCommentById(Integer id) throws GetException, JsonProcessingException {
        return null;
    }

    @Override
    public CommentDto getCommentById(Integer id) throws GetException, JsonProcessingException {
        return null;
    }

    @Override
    public CommentDto addComment(Comment comment) {
        return null;
    }

    @Override
    public Integer updateCommentById(Integer id, Comment comment) {
        return null;
    }

    @Override
    public Integer deleteCommentById(Integer id) {
        return null;
    }
    private Comment extractCommentFromResultSet(ResultSet resultSet) throws SQLException {
        Integer columnId = resultSet.getInt("id");
        Integer columnServiceUserId = resultSet.getInt("service_user_id");
        Integer columnArticleId = resultSet.getInt("article_id");
        String columnBody = resultSet.getString("body");
        Timestamp columnTimePublished = resultSet.getTimestamp("time_published");
        return new Comment(columnId, columnServiceUserId, columnArticleId, columnBody, columnTimePublished);
    }
}
