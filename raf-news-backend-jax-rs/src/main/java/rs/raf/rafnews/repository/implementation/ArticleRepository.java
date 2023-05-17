package rs.raf.rafnews.repository.implementation;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.model.Article;
import rs.raf.rafnews.repository.specification.IArticleRepository;
import rs.raf.rafnews.service.specification.ICategoryService;
import rs.raf.rafnews.service.specification.IServiceUserService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class ArticleRepository implements IArticleRepository {
    @Inject
    private IServiceUserService serviceUserService;
    @Inject
    private ICategoryService categoryService;
    @Override
    public List<ArticleDto> getAllArticles() {
        List<ArticleDto> articleDtoList = new ArrayList<>();
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
                    /*ArticleDto articleDto = new ArticleDto(id, service_user_id, category_id, title, content, time_published, views);
                    articleDtoList.add(article);*/
                }
            }
            catch (SQLException e) {
                return null;
            }
        }
        catch (SQLException e) {
            return null;
        }
        return articleDtoList;
    }

    @Override
    public ArticleDto getArticleById(Integer id) {
        return null;
    }

    @Override
    public ArticleDto addArticle(Article article) {
        return null;
    }

    @Override
    public ArticleDto updateArticle(Article article) {
        return null;
    }

    @Override
    public boolean deleteArticleById(Integer id) {
        return false;
    }
}
