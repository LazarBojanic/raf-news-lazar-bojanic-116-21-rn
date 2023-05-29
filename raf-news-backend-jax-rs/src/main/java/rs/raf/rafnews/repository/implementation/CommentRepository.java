package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.*;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.model.Comment;
import rs.raf.rafnews.repository.specification.ICommentRepository;
import rs.raf.rafnews.request.CommentRequest;
import rs.raf.rafnews.service.specification.IArticleService;
import rs.raf.rafnews.service.specification.IServiceUserService;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CommentRepository implements ICommentRepository {
    @Inject
    private IServiceUserService serviceUserService;
    @Inject
    private IArticleService articleService;
    @Override
    public List<Comment> getAllRawComments() throws GetException, JsonProcessingException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        List<Comment> commentList = new ArrayList<>();
        String query = "SELECT * FROM comment";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
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
        finally {
            connection.close();
        }
        return commentList;
    }

    @Override
    public List<CommentDto> getAllComments() throws GetException, JsonProcessingException, JoinException, SQLException {
        List<Comment> commentList = getAllRawComments();
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList){
            CommentDto commentDto = joinComment(comment);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }

    @Override
    public List<Comment> getAllRawCommentsByArticleId(Integer articleId) throws GetException, JsonProcessingException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        List<Comment> commentList = new ArrayList<>();
        String query = "SELECT * FROM comment WHERE article_id = ?";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, articleId);
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
        finally {
            connection.close();
        }
        return commentList;
    }

    @Override
    public List<CommentDto> getAllCommentsByArticleId(Integer articleId) throws GetException, JsonProcessingException, JoinException, SQLException {
        List<Comment> commentList = getAllRawCommentsByArticleId(articleId);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList){
            commentDtoList.add(joinComment(comment));
        }
        return commentDtoList;
    }

    @Override
    public CommentDto joinComment(Comment comment) throws JsonProcessingException, JoinException {
        try{
            boolean exceptionOccurred = false;
            String exceptionMessageString = "Failed to join comment. ";
            ServiceUserDto serviceUserDto = serviceUserService.getServiceUserById(comment.getService_user_id());
            ArticleDto articleDto = articleService.getArticleById(comment.getArticle_id());
            if(serviceUserDto.getId() <= 0){
                exceptionMessageString += "Couldn't find a user with id = " + comment.getService_user_id() + ". ";
                exceptionOccurred = true;
            }
            if(articleDto.getId() <= 0){
                exceptionMessageString += "Couldn't find a article with id = " + comment.getArticle_id() + ". ";
                exceptionOccurred = true;
            }
            if(!exceptionOccurred){
                return new CommentDto(comment, serviceUserDto, articleDto);
            }
            ExceptionMessage exceptionMessage = new ExceptionMessage("JoinException", exceptionMessageString);
            throw new JoinException(exceptionMessage);
        }
        catch (Exception e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("JoinException", e.getMessage());
            throw new JoinException(exceptionMessage);
        }
    }

    @Override
    public Comment getRawCommentById(Integer id) throws GetException, JsonProcessingException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "SELECT * FROM comment WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return extractCommentFromResultSet(resultSet);
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
        return new Comment();
    }

    @Override
    public CommentDto getCommentById(Integer id) throws GetException, JsonProcessingException, JoinException, SQLException {
        Comment comment = getRawCommentById(id);
        return joinComment(comment);
    }

    @Override
    public Comment addRawComment(Comment comment) throws SQLException, JsonProcessingException, AddException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        try{
            String query = "INSERT INTO comment(service_user_id, article_id, body, time_published) VALUES(?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, comment.getService_user_id());
                preparedStatement.setInt(2, comment.getArticle_id());
                preparedStatement.setString(3, comment.getBody());
                preparedStatement.setTimestamp(4, comment.getTime_published());
                if(preparedStatement.executeUpdate() > 0){
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int id = generatedKeys.getInt("id");
                            comment.setId(id);
                            return comment;
                        }
                    }
                }
            }
            ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", "Failed to add comment: " + comment);
            throw new AddException(exceptionMessage);
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", e.getMessage());
            throw new AddException(exceptionMessage);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public CommentDto addCommentToArticle(CommentRequest commentRequest) throws SQLException, AddException, JsonProcessingException, JoinException {
        Comment comment = new Comment(-1, commentRequest.getService_user_id(), commentRequest.getArticle_id(), commentRequest.getBody(), Timestamp.from(Instant.now()));
        return joinComment(addRawComment(comment));
    }

    @Override
    public Integer updateCommentById(Integer id, Comment comment) {
        return null;
    }

    @Override
    public Integer deleteCommentById(Integer id) throws SQLException, JsonProcessingException, DeleteException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        try{
            String query = "DELETE FROM comment WHERE id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1, id);
                int affectedRows = preparedStatement.executeUpdate();
                if(affectedRows > 0){
                    return affectedRows;
                }
                else{
                    ExceptionMessage exceptionMessage = new ExceptionMessage("DeleteException", "Failed to delete comment. Id " + id + " not found.");
                    throw new DeleteException(exceptionMessage);
                }
            }
        }
        catch (SQLException e){
            ExceptionMessage exceptionMessage = new ExceptionMessage("DeleteException", e.getMessage());
            throw new DeleteException(exceptionMessage);
        }
    }

    @Override
    public Integer deleteAllCommentsByArticleId(Integer articleId) throws SQLException, DeleteException, JsonProcessingException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        try{
            String query = "DELETE FROM comment WHERE article_id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1, articleId);
                return preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e){
            ExceptionMessage exceptionMessage = new ExceptionMessage("DeleteException", e.getMessage());
            throw new DeleteException(exceptionMessage);
        }
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
