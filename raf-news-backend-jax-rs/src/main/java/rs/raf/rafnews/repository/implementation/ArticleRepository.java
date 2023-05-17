package rs.raf.rafnews.repository.implementation;

import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.repository.specification.IArticleRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class ArticleRepository implements IArticleRepository {
    @Override
    public List<Article> getAllArticles() {
        List<Article> articleList = new ArrayList<>();
        String query = "SELECT * FROM article";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    Integer service_user_id = resultSet.getInt("service_user_id");
                    Integer category_id = resultSet.getInt("category_id");
                    String title = resultSet.getString("title");
                    String content = resultSet.getString("content");
                    Timestamp time_published = resultSet.getTimestamp("time_published");
                    Integer views = resultSet.getInt("views");
                    Article article = new Article(id, service_user_id, category_id, title, content, time_published, views);
                    articleList.add(article);
                }
            }
            catch (SQLException e) {
                return null;
            }
        }
        catch (SQLException e) {
            return null;
        }
        return articleList;
    }

    @Override
    public Article getArticleById(Integer id) {
        return null;
    }

    @Override
    public Article addArticle(Article article) {
        return null;
    }

    @Override
    public Article updateArticle(Article article) {
        return null;
    }

    @Override
    public boolean deleteArticleById(Integer id) {
        return false;
    }
}
