package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.ArticleWithTagDto;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.dto.ServiceUserDto;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.repository.specification.IArticleRepository;
import rs.raf.rafnews.request.ArticleRequest;
import rs.raf.rafnews.request.ArticleSearchRequest;
import rs.raf.rafnews.service.specification.*;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ArticleRepository implements IArticleRepository {
    @Inject
    private IServiceUserService serviceUserService;
    @Inject
    private ICategoryService categoryService;
    @Inject
    private ITagService tagService;
    @Inject
    private IArticleWithTagService articleWithTagService;
    @Inject
    private ICommentService commentService;

    @Override
    public List<Article> getAllRawArticles() throws JsonProcessingException, GetException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        List<Article> articleList = new ArrayList<>();
        String query = "SELECT * FROM article";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
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
        finally {
            connection.close();
        }
        return articleList;
    }

    @Override
    public List<ArticleDto> getAllArticles() throws GetException, JsonProcessingException, JoinException, SQLException {
        List<Article> articleList = getAllRawArticles();
        List<ArticleDto> articleDtoList = new ArrayList<>();
        for(Article article : articleList){
            ArticleDto articleDto = joinArticle(article);
            articleDtoList.add(articleDto);
        }
        return articleDtoList;
    }

    @Override
    public List<ArticleDto> getAllArticlesFiltered(ArticleSearchRequest articleSearchRequest) throws GetException, JoinException, SQLException, JsonProcessingException {
        List<ArticleDto> articleDtoList = getAllArticles();

        List<ArticleDto> articleDtoListFiltered = articleDtoList.stream()
                .filter(articleDto -> {
                    // Check category name filter
                    String categoryName = articleSearchRequest.getCategory_name();
                    if (categoryName != null && !categoryName.isEmpty()) {
                        return articleDto.getCategory().getCategory_name().equalsIgnoreCase(categoryName);
                    }
                    return true; // No category name filter or empty category name
                })
                .skip((long) (articleSearchRequest.getPage() - 1) * articleSearchRequest.getPage_size())
                .limit(articleSearchRequest.getPage_size())
                .collect(Collectors.toList());

        return articleDtoListFiltered;
    }

    @Override
    public ArticleDto joinArticle(Article article) throws JsonProcessingException, JoinException {
        try{
            boolean exceptionOccurred = false;
            String exceptionMessageString = "Failed to join article. ";
            ServiceUserDto serviceUserDto = serviceUserService.getServiceUserById(article.getService_user_id());
            CategoryDto categoryDto = categoryService.getCategoryById(article.getCategory_id());
            List<ArticleWithTagDto> articleWithTagDtoList = articleWithTagService.getAllArticlesWithTagByByArticleId(article.getId());
            if(serviceUserDto.getId() <= 0){
                exceptionMessageString += "Couldn't find a category with id = " + article.getCategory_id() + ". ";
                exceptionOccurred = true;
            }
            if(categoryDto.getId() <= 0){
                exceptionMessageString += "Couldn't find a category with id = " + article.getCategory_id() + ". ";
                exceptionOccurred = true;
            }
            if(!exceptionOccurred){
                return new ArticleDto(article, serviceUserDto, categoryDto, articleWithTagDtoList);
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
    public Article getRawArticleById(Integer id) throws JsonProcessingException, GetException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "SELECT * FROM article WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
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
        finally {
            connection.close();
        }
        return new Article();
    }

    @Override
    public Article getRawArticleByCategoryId(Integer categoryId) throws GetException, JsonProcessingException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "SELECT * FROM article WHERE category_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, categoryId);
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
        finally {
            connection.close();
        }
        return new Article();
    }

    @Override
    public ArticleDto getArticleById(Integer id) throws GetException, JsonProcessingException, JoinException, SQLException {
        Article article = getRawArticleById(id);
        if(article.getId() > 0){
            return joinArticle(article);
        }
        return new ArticleDto();
    }

    @Override
    public Article addRawArticle(Article article) throws JsonProcessingException, AddException, GetException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        try{
            ServiceUserDto serviceUserDto = serviceUserService.getServiceUserById(article.getService_user_id());
            CategoryDto categoryDto = categoryService.getCategoryById(article.getCategory_id());
            if(serviceUserDto.getId() > 0 && categoryDto.getId() > 0){
                String query = "INSERT INTO article(service_user_id, category_id, title, body, time_published, number_of_views) VALUES(?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setInt(1, article.getService_user_id());
                    preparedStatement.setInt(2, article.getCategory_id());
                    preparedStatement.setString(3, article.getTitle());
                    preparedStatement.setString(4, article.getBody());
                    preparedStatement.setTimestamp(5, article.getTime_published());
                    preparedStatement.setInt(6, article.getNumber_of_views());
                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {
                        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int id = generatedKeys.getInt("id");
                                article.setId(id);
                                return article;
                            }
                        }
                    }
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
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", e.getMessage());
            throw new AddException(exceptionMessage);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public ArticleDto addArticle(ArticleRequest articleRequest) throws AddException, JsonProcessingException, GetException, JoinException, SQLException {
        Integer categoryId = categoryService.getCategoryByCategoryName(articleRequest.getCategory_name()).getId();
        if(categoryId > 0){
            Article addedRawArticle = addRawArticle(new Article(articleRequest.getId(), articleRequest.getService_user_id(), categoryId, articleRequest.getTitle(), articleRequest.getBody(), Timestamp.from(Instant.now()), 0));
            if(addedRawArticle.getId() > 0){
                List<Tag> addedTagList = tagService.addTagList(articleRequest.getTag_list());
                articleWithTagService.addTagListToArticle(addedRawArticle.getId(), articleRequest.getTag_list());
                return joinArticle(addedRawArticle);
            }
        }
        else{
            ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", "Failed to add article. Category with name: " + articleRequest.getCategory_name() + " not found.");
            throw new AddException(exceptionMessage);
        }
        return new ArticleDto();
    }

    @Override
    public Integer updateArticleById(Integer id, ArticleRequest articleRequest) throws JsonProcessingException, UpdateException {
        try {
            if(articleRequest.getCategory_name() != null){
                Integer categoryId = categoryService.getRawCategoryByCategoryName(articleRequest.getCategory_name()).getId();
                if(categoryId > 0){
                    Article currentArticle = getRawArticleById(id);
                    if(currentArticle.getId() > 0){
                        Article newArticle = new Article(articleRequest.getId(), articleRequest.getService_user_id(), categoryId, articleRequest.getTitle(), articleRequest.getBody(), Timestamp.from(Instant.now()), 0);
                        Class<?> articleClass = Article.class;
                        Field[] fields = articleClass.getDeclaredFields();
                        for (Field field : fields) {
                            field.setAccessible(true);
                            Object newValue = field.get(newArticle);
                            if (newValue != null) {
                                field.set(currentArticle, newValue);
                            }
                        }
                        if(articleRequest.getTag_list() != null){
                            tagService.addTagList(articleRequest.getTag_list());
                            articleWithTagService.updateTagsForArticle(articleRequest.getId(), articleRequest.getTag_list());
                        }
                        return saveArticle(currentArticle);
                    }
                    else{
                        ExceptionMessage exceptionMessage = new ExceptionMessage("UpdateException", "Failed to update article with id: " + id + ". Id not found");
                        throw new UpdateException(exceptionMessage);
                    }
                }
                else{
                    ExceptionMessage exceptionMessage = new ExceptionMessage("UpdateException", "Failed to update article with id: " + id + ". Category not found");
                    throw new UpdateException(exceptionMessage);
                }
            }
            else{
                ExceptionMessage exceptionMessage = new ExceptionMessage("UpdateException", "Failed to update article. Category may not be null");
                throw new UpdateException(exceptionMessage);
            }
        }
        catch (Exception e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("UpdateException", "Failed to update article with id: " + id + " with new article: " + articleRequest + ". " + e.getMessage());
            throw new UpdateException(exceptionMessage);
        }
    }

    @Override
    public Integer saveArticle(Article article) throws JsonProcessingException, UpdateException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "UPDATE article SET service_user_id = ?, category_id = ?, title = ?, body = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, article.getService_user_id());
            preparedStatement.setInt(2, article.getCategory_id());
            preparedStatement.setString(3, article.getTitle());
            preparedStatement.setString(4, article.getBody());
            preparedStatement.setInt(5, article.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows >= 0){
                return affectedRows;
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("UpdateException", e.getMessage());
            throw new UpdateException(exceptionMessage);
        }
        finally {
            connection.close();
        }
        return -1;
    }

    @Override
    public Integer deleteArticleById(Integer id) throws JsonProcessingException, DeleteException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        try{
            String query = "DELETE FROM article WHERE id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1, id);
                int affectedRows = preparedStatement.executeUpdate();
                commentService.deleteAllCommentsByArticleId(id);
                return affectedRows;
            }
        }
        catch (SQLException e){
            ExceptionMessage exceptionMessage = new ExceptionMessage("DeleteException", e.getMessage());
            throw new DeleteException(exceptionMessage);
        }
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
