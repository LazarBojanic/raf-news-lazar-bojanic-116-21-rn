package rs.raf.rafnews.service.implementation;

import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.model.Comment;
import rs.raf.rafnews.service.specification.ICommentService;

import java.util.List;

@RequestScoped
public class CommentService implements ICommentService {
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
