package rs.raf.rafnews.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.CommentDto;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Comment;
import rs.raf.rafnews.repository.specification.ICommentRepository;
import rs.raf.rafnews.service.specification.ICommentService;

import java.util.List;

@RequestScoped
public class CommentService implements ICommentService {
    @Inject
    private ICommentRepository commentRepository;
    @Override
    public List<Comment> getAllRawComments() throws GetException, JsonProcessingException {
        return this.commentRepository.getAllRawComments();
    }

    @Override
    public List<CommentDto> getAllComments() throws GetException, JsonProcessingException {
        return this.commentRepository.getAllComments();
    }

    @Override
    public List<Comment> getAllRawCommentsByArticleId(Integer articleId) throws GetException, JsonProcessingException {
        return this.commentRepository.getAllRawCommentsByArticleId(articleId);
    }

    @Override
    public List<CommentDto> getAllCommentsByArticleId(Integer articleId) throws GetException, JsonProcessingException {
        return this.commentRepository.getAllCommentsByArticleId(articleId);
    }

    @Override
    public CommentDto joinComment(Comment comment) {
        return this.commentRepository.joinComment(comment);
    }

    @Override
    public Comment getRawCommentById(Integer id) throws GetException, JsonProcessingException {
        return this.commentRepository.getRawCommentById(id);
    }

    @Override
    public CommentDto getCommentById(Integer id) throws GetException, JsonProcessingException {
        return this.commentRepository.getCommentById(id);
    }

    @Override
    public CommentDto addComment(Comment comment) {
        return this.commentRepository.addComment(comment);
    }

    @Override
    public Integer updateCommentById(Integer id, Comment comment) {
        return this.commentRepository.updateCommentById(id, comment);
    }

    @Override
    public Integer deleteCommentById(Integer id) {
        return this.commentRepository.deleteCommentById(id);
    }
}
