package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.dto.ServiceUserDto;
import rs.raf.rafnews.dto.TagDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.ExceptionMessage;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.JoinException;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.repository.specification.IArticleRepository;
import rs.raf.rafnews.service.specification.IArticleWithTagService;
import rs.raf.rafnews.service.specification.ICategoryService;
import rs.raf.rafnews.service.specification.IServiceUserService;
import rs.raf.rafnews.service.specification.ITagService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class ArticleRepository implements IArticleRepository {
    @Inject
    private IServiceUserService serviceUserService;
    @Inject
    private ICategoryService categoryService;
    @Inject
    private ITagService tagService;


    @Override
    public List<Article> getAllRawArticles() throws JsonProcessingException, GetException {
        List<Article> articleList = new ArrayList<>();
        String query = "SELECT * FROM article";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Article article = extractArticleFromResultSet(resultSet);
                    articleList.add(article);
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        return articleList;
    }

    @Override
    public List<ArticleDto> getAllArticles() throws GetException, JsonProcessingException, JoinException {
        List<Article> articleList = getAllRawArticles();
        List<ArticleDto> articleDtoList = new ArrayList<>();
        for(Article article : articleList){
            ArticleDto articleDto = joinArticle(article);
            articleDtoList.add(articleDto);
        }
        return articleDtoList;
    }
    @Override
    public ArticleDto joinArticle(Article article) throws JsonProcessingException, JoinException {
        try{
            boolean exceptionOccurred = false;
            String exceptionMessageString = "Failed to join article. ";
            ServiceUserDto serviceUserDto = serviceUserService.getServiceUserById(article.getService_user_id());
            CategoryDto categoryDto = categoryService.getCategoryById(article.getCategory_id());
            List<TagDto> tagDtoList = tagService.getAllTagsByArticleId(article.getId());
            if(serviceUserDto.getId() <= 0){
                exceptionMessageString += "Couldn't find a category with id = " + article.getCategory_id() + ". ";
                exceptionOccurred = true;
            }
            if(categoryDto.getId() <= 0){
                exceptionMessageString += "Couldn't find a category with id = " + article.getCategory_id() + ". ";
                exceptionOccurred = true;
            }
            if(!exceptionOccurred){
                return new ArticleDto(article, serviceUserDto, categoryDto, tagDtoList);
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
    public Article getRawArticleById(Integer id) throws JsonProcessingException, GetException {
        String query = "SELECT * FROM article WHERE id = ?";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return extractArticleFromResultSet(resultSet);
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        return new Article();
    }

    @Override
    public ArticleDto getArticleById(Integer id) throws GetException, JsonProcessingException, JoinException {
        Article article = getRawArticleById(id);
        if(article.getId() > 0){
            return joinArticle(article);
        }
        else{
            return new ArticleDto();
        }
    }

    @Override
    public Article addRawArticle(Article article) throws JsonProcessingException, AddException, GetException {
        ServiceUserDto serviceUserDto = serviceUserService.getServiceUserById(article.getService_user_id());
        CategoryDto categoryDto = categoryService.getCategoryById(article.getCategory_id());
        if(serviceUserDto.getId() > 0 && categoryDto.getId() > 0){
            String query = "INSERT INTO article(service_user_id, category_id, title, body, time_published, number_of_views) VALUES(?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, article.getService_user_id());
                preparedStatement.setInt(2, article.getCategory_id());
                preparedStatement.setString(3, article.getTitle());
                preparedStatement.setString(4, article.getBody());
                preparedStatement.setTimestamp(5, article.getTime_published());
                preparedStatement.setInt(6, article.getNumber_of_views());
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt("id");
                        article.setId(id);
                        return article;
                    }
                }
            }
            catch (SQLException e) {
                ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", e.getMessage());
                throw new AddException(exceptionMessage);
            }
            ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", "Failed to add article: " + article);
            throw new AddException(exceptionMessage);
        }
        else if(serviceUserDto.getId() <= 0 && categoryDto.getId() > 0){
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", "Failed to get user with id: " + serviceUserDto.getId());
            throw new GetException(exceptionMessage);
        }
        else if(serviceUserDto.getId() > 0 && categoryDto.getId() <= 0) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", "Failed to get category with id: " + categoryDto.getId());
            throw new GetException(exceptionMessage);
        }
        else{
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", "Failed to get user with id: " + serviceUserDto.getId() + ", and category with id: " + categoryDto.getId());
            throw new GetException(exceptionMessage);
        }
    }

    @Override
    public ArticleDto addArticle(Article article) throws AddException, JsonProcessingException, GetException, JoinException {
        Article addedArticle = addRawArticle(article);
        return joinArticle(addedArticle);
    }

    @Override
    public Integer updateArticleById(Integer id, Article article) {
        return null;
    }

    @Override
    public Integer deleteArticleById(Integer id) {
        return null;
    }
    private Article extractArticleFromResultSet(ResultSet resultSet) throws SQLException {
        Integer columnId = resultSet.getInt("id");
        Integer columnServiceUserId = resultSet.getInt("service_user_id");
        Integer columnCategoryId = resultSet.getInt("category_id");
        String columnTitle = resultSet.getString("title");
        String columnBody = resultSet.getString("body");
        Timestamp columnTimePublished = resultSet.getTimestamp("time_published");
        Integer columnNumberOfViews = resultSet.getInt("number_of_views");
        return new Article(columnId, columnServiceUserId, columnCategoryId, columnTitle, columnBody, columnTimePublished, columnNumberOfViews);
    }
}
