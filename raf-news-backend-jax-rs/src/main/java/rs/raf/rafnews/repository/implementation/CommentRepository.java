package rs.raf.rafnews.repository.implementation;

import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.model.Comment;
import rs.raf.rafnews.repository.specification.ICommentRepository;

import java.util.List;

@RequestScoped
public class CommentRepository implements ICommentRepository {
    @Override
    public List<Comment> getAllComments() {
        return null;
    }

    @Override
    public Comment getCommentById(Integer id) {
        return null;
    }

    @Override
    public Comment addComment(Comment article) {
        return null;
    }

    @Override
    public Comment updateComment(Comment article) {
        return null;
    }

    @Override
    public boolean deleteCommentById(Integer id) {
        return false;
    }
}
