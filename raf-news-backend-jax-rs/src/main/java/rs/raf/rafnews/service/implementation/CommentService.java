package rs.raf.rafnews.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.CommentDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.DeleteException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.Comment;
import rs.raf.rafnews.repository.specification.ICommentRepository;
import rs.raf.rafnews.request.CommentRequest;
import rs.raf.rafnews.request.CommentSearchRequest;
import rs.raf.rafnews.service.specification.ICommentService;

import java.sql.SQLException;
import java.util.List;

@RequestScoped
public class CommentService implements ICommentService {
    @Inject
    private ICommentRepository commentRepository;
    @Override
    public List<Comment> getAllRawComments() throws GetException, JsonProcessingException, SQLException {
        return this.commentRepository.getAllRawComments();
    }

    @Override
    public List<CommentDto> getAllComments() throws GetException, JsonProcessingException, JoinException, SQLException {
        return this.commentRepository.getAllComments();
    }

    @Override
    public List<CommentDto> getAllCommentsFiltered(CommentSearchRequest commentSearchRequest) throws GetException, JsonProcessingException, JoinException, SQLException {
        return this.commentRepository.getAllCommentsFiltered(commentSearchRequest);
    }

    @Override
    public List<Comment> getAllRawCommentsByArticleId(Integer articleId) throws GetException, JsonProcessingException, SQLException {
        return this.commentRepository.getAllRawCommentsByArticleId(articleId);
    }

    @Override
    public List<CommentDto> getAllCommentsByArticleId(Integer articleId) throws GetException, JsonProcessingException, JoinException, SQLException {
        return this.commentRepository.getAllCommentsByArticleId(articleId);
    }

    @Override
    public List<CommentDto> getAllCommentsByArticleIdFiltered(Integer articleId, CommentSearchRequest commentSearchRequest) throws GetException, JsonProcessingException, JoinException, SQLException {
        return this.commentRepository.getAllCommentsByArticleIdFiltered(articleId, commentSearchRequest);
    }

    @Override
    public CommentDto joinComment(Comment comment) throws JoinException, JsonProcessingException {
        return this.commentRepository.joinComment(comment);
    }

    @Override
    public Comment getRawCommentById(Integer id) throws GetException, JsonProcessingException, SQLException {
        return this.commentRepository.getRawCommentById(id);
    }

    @Override
    public CommentDto getCommentById(Integer id) throws GetException, JsonProcessingException, JoinException, SQLException {
        return this.commentRepository.getCommentById(id);
    }

    @Override
    public Comment addRawComment(Comment comment) throws SQLException, AddException, JsonProcessingException {
        return this.commentRepository.addRawComment(comment);
    }

    @Override
    public CommentDto addCommentToArticle(CommentRequest commentRequest) throws JoinException, SQLException, AddException, JsonProcessingException {
        return this.commentRepository.addCommentToArticle(commentRequest);
    }

    @Override
    public Integer updateCommentById(Integer id, Comment comment) {
        return this.commentRepository.updateCommentById(id, comment);
    }

    @Override
    public Integer deleteCommentById(Integer id) throws SQLException, DeleteException, JsonProcessingException {
        return this.commentRepository.deleteCommentById(id);
    }

    @Override
    public Integer deleteAllCommentsByArticleId(Integer articleId) throws SQLException, DeleteException, JsonProcessingException {
        return this.commentRepository.deleteAllCommentsByArticleId(articleId);
    }
}
