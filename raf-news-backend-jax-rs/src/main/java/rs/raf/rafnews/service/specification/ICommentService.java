package rs.raf.rafnews.service.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.CommentDto;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Comment;
import rs.raf.rafnews.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getAllRawComments() throws GetException, JsonProcessingException;
    List<CommentDto> getAllComments() throws GetException, JsonProcessingException;
    List<Comment> getAllRawCommentsByArticleId(Integer articleId) throws GetException, JsonProcessingException;
    List<CommentDto> getAllCommentsByArticleId(Integer articleId) throws GetException, JsonProcessingException;
    CommentDto joinComment(Comment comment);
    Comment getRawCommentById(Integer id) throws GetException, JsonProcessingException;
    CommentDto getCommentById(Integer id) throws GetException, JsonProcessingException;
    CommentDto addComment(Comment comment);
    Integer updateCommentById(Integer id, Comment comment);
    Integer deleteCommentById(Integer id);
}