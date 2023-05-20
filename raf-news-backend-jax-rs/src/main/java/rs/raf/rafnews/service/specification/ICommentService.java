package rs.raf.rafnews.service.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.CommentDto;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.Comment;
import rs.raf.rafnews.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface ICommentService {
    List<Comment> getAllRawComments() throws GetException, JsonProcessingException, SQLException;
    List<CommentDto> getAllComments() throws GetException, JsonProcessingException, JoinException, SQLException;
    List<Comment> getAllRawCommentsByArticleId(Integer articleId) throws GetException, JsonProcessingException, SQLException;
    List<CommentDto> getAllCommentsByArticleId(Integer articleId) throws GetException, JsonProcessingException, JoinException, SQLException;
    CommentDto joinComment(Comment comment) throws JoinException, JsonProcessingException;
    Comment getRawCommentById(Integer id) throws GetException, JsonProcessingException, SQLException;
    CommentDto getCommentById(Integer id) throws GetException, JsonProcessingException, JoinException, SQLException;
    CommentDto addComment(Comment comment);
    Integer updateCommentById(Integer id, Comment comment);
    Integer deleteCommentById(Integer id);
}