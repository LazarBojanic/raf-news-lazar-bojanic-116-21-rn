package rs.raf.rafnews.repository.specification;

import rs.raf.rafnews.model.Comment;

import java.util.List;

public interface ICommentRepository {
    List<Comment> getAllComments();
    Comment getCommentById(Integer id);
    Comment addComment(Comment article);
    Comment updateComment(Comment article);
    boolean deleteCommentById(Integer id);
}