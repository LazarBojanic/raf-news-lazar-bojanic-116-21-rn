package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.ArticleDto;
import rs.raf.rafnews.dto.ServiceUserDto;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.repository.specification.IServiceUserRepository;
import rs.raf.rafnews.request.*;
import rs.raf.rafnews.service.specification.IArticleService;
import rs.raf.rafnews.service.specification.ICommentService;
import rs.raf.rafnews.util.Hasher;
import rs.raf.rafnews.util.Util;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestScoped
public class ServiceUserRepository implements IServiceUserRepository {
    private static final String JWT_SECRET = "NQu2mzEtCwrNaJCjsoHT";
    public static final String MASTER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsInBhc3MiOiJhZG1pbiJ9.EcbsD0Wn1wkI8iVVTEOX0IWHuwyqOndzPUFtDAM4TMI";
    @Inject
    private IArticleService articleService;
    @Inject
    private ICommentService commentService;
    @Override
    public List<ServiceUser> getAllRawServiceUsers() throws JsonProcessingException, GetException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        List<ServiceUser> serviceUserList = new ArrayList<>();
        String query = "SELECT * FROM service_user";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    ServiceUser serviceUser = extractServiceUserFromResultSet(resultSet);
                    serviceUserList.add(serviceUser);
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
        return serviceUserList;
    }

    @Override
    public List<ServiceUserDto> getAllServiceUsers() throws JsonProcessingException, GetException, SQLException {
        List<ServiceUser> serviceUserList = getAllRawServiceUsers();
        List<ServiceUserDto> serviceUserDtoList = new ArrayList<>();
        for(ServiceUser serviceUser : serviceUserList){
            serviceUserDtoList.add(joinServiceUser(serviceUser));
        }
        return serviceUserDtoList;
    }

    @Override
    public List<ServiceUserDto> getAllServiceUsersFiltered(ServiceUserSearchRequest serviceUserSearchRequest) throws JsonProcessingException, GetException, SQLException {
        List<ServiceUserDto> serviceUserDtoList = getAllServiceUsers();
        return serviceUserDtoList.stream()
                .skip((long) (serviceUserSearchRequest.getPage() - 1) * serviceUserSearchRequest.getPage_size())
                .limit(serviceUserSearchRequest.getPage_size())
                .collect(Collectors.toList());
    }

    @Override
    public ServiceUserDto joinServiceUser(ServiceUser serviceUser) {
        return new ServiceUserDto(serviceUser);
    }

    @Override
    public ServiceUser getRawServiceUserById(Integer id) throws JsonProcessingException, GetException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "SELECT * FROM service_user WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return extractServiceUserFromResultSet(resultSet);
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
        return new ServiceUser();
    }
    @Override
    public ServiceUserDto getServiceUserById(Integer id) throws JsonProcessingException, GetException, SQLException {
        ServiceUser serviceUser = getRawServiceUserById(id);
        return joinServiceUser(serviceUser);
    }


    @Override
    public ServiceUser getRawServiceUserByEmail(String email) throws JsonProcessingException, GetException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "SELECT * FROM service_user WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, email);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return extractServiceUserFromResultSet(resultSet);
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
        return new ServiceUser();
    }
    @Override
    public ServiceUserDto getServiceUserByEmail(String email) throws JsonProcessingException, GetException, SQLException {
        ServiceUser serviceUser = getRawServiceUserByEmail(email);
        return joinServiceUser(serviceUser);
    }

    @Override
    public ServiceUser getRawServiceUserByUsername(String username) throws JsonProcessingException, GetException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "SELECT * FROM service_user WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, username);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return extractServiceUserFromResultSet(resultSet);
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
        return new ServiceUser();
    }

    @Override
    public ServiceUser getRawServiceUserByEmailOrUsername(String email, String username) throws JsonProcessingException, GetException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "SELECT * FROM service_user WHERE email = ? OR username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return extractServiceUserFromResultSet(resultSet);
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
        return new ServiceUser();
    }

    @Override
    public ServiceUserDto addServiceUser(ServiceUser serviceUser) throws JsonProcessingException, AddException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "INSERT INTO service_user(username, email, pass, user_role, is_enabled, first_name, last_name) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, serviceUser.getUsername());
            preparedStatement.setString(2, serviceUser.getEmail());
            preparedStatement.setString(3, serviceUser.getPass());
            preparedStatement.setString(4, serviceUser.getUser_role());
            preparedStatement.setString(5, serviceUser.getIs_enabled());
            preparedStatement.setString(6, serviceUser.getFirst_name());
            preparedStatement.setString(7, serviceUser.getLast_name());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt("id");
                        serviceUser.setId(id);
                        return new ServiceUserDto(serviceUser);
                    }
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", e.getMessage());
            throw new AddException(exceptionMessage);
        }
        finally {
            connection.close();
        }
        ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", "Failed to add user: " + serviceUser);
        throw new AddException(exceptionMessage);
    }

    @Override
    public Integer updateServiceUserById(Integer id, ServiceUser serviceUser) throws JsonProcessingException, UpdateException, GetException {
        try {
            ServiceUser currentUser = getRawServiceUserById(id);
            if(currentUser.getId() > 0){
                Class<?> serviceUserClass = ServiceUser.class;
                Field[] fields = serviceUserClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object newValue = field.get(serviceUser);
                    if (newValue != null) {
                        field.set(currentUser, newValue);
                    }
                }
                return saveServiceUser(currentUser);
            }
            else{
                ExceptionMessage exceptionMessage = new ExceptionMessage("UpdateException", "Failed to update user with id: " + id + " with new user: " + serviceUser);
                throw new UpdateException(exceptionMessage);
            }
        }
        catch (Exception e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("UpdateException", "Failed to update user with id: " + id + " with new user: " + serviceUser + ". " + e.getMessage());
            throw new UpdateException(exceptionMessage);
        }
    }

    @Override
    public Integer switchServiceUserEnabledById(Integer id, ServiceUserSwitchEnabledRequest serviceUserSwitchEnabledRequest) throws JsonProcessingException, UpdateException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "UPDATE service_user SET is_enabled = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, serviceUserSwitchEnabledRequest.getIs_enabled());
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("UpdateException", "Failed to switch enabled for user with id: " + id);
            throw new UpdateException(exceptionMessage);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public ServiceUserDto registerServiceUser(RegisterRequest registerRequest) throws JsonProcessingException, RegisterException, GetException, SQLException, AddException {
        if(registerRequest.getPass().equals(registerRequest.getConfirm_pass())){
            if(getRawServiceUserByEmailOrUsername(registerRequest.getEmail(), registerRequest.getUsername()).getId() == -1){
                String hashedPass = Hasher.hashPassword(registerRequest.getPass());
                return addServiceUser(new ServiceUser(0, registerRequest.getUsername(), registerRequest.getEmail(), hashedPass, Util.ROLE_CONTENT_CREATOR, "true", registerRequest.getFirst_name(), registerRequest.getLast_name()));
            }
            else{
                ExceptionMessage exceptionMessage = new ExceptionMessage("RegisterException", "Failed to register. Email: " + registerRequest.getEmail()  + " or username: " + registerRequest.getUsername() + " already exist.");
                throw new RegisterException(exceptionMessage);
            }
        }
        else{
            ExceptionMessage exceptionMessage = new ExceptionMessage("RegisterException", "Failed to register. Passwords must match.");
            throw new RegisterException(exceptionMessage);
        }
    }

    @Override
    public ServiceUserDto registerServiceUserFromAdmin(RegisterFromAdminRequest registerFromAdminRequest) throws JsonProcessingException, RegisterException, GetException, SQLException, AddException {
        if(registerFromAdminRequest.getPass().equals(registerFromAdminRequest.getConfirm_pass())){
            if(getRawServiceUserByEmailOrUsername(registerFromAdminRequest.getEmail(), registerFromAdminRequest.getUsername()).getId() == -1){
                String hashedPass = Hasher.hashPassword(registerFromAdminRequest.getPass());
                return addServiceUser(new ServiceUser(0, registerFromAdminRequest.getUsername(), registerFromAdminRequest.getEmail(), hashedPass, registerFromAdminRequest.getUser_role(), "true", registerFromAdminRequest.getFirst_name(), registerFromAdminRequest.getLast_name()));
            }
            else{
                ExceptionMessage exceptionMessage = new ExceptionMessage("RegisterException", "Failed to register. Email: " + registerFromAdminRequest.getEmail()  + " or username: " + registerFromAdminRequest.getUsername() + " already exist.");
                throw new RegisterException(exceptionMessage);
            }
        }
        else{
            ExceptionMessage exceptionMessage = new ExceptionMessage("RegisterException", "Failed to register. Passwords must match.");
            throw new RegisterException(exceptionMessage);
        }
    }

    @Override
    public Token loginServiceUser(LoginRequest loginRequest) throws LoginException, JsonProcessingException, GetException, SQLException, TokenGenerateException {
        ServiceUser serviceUser = getRawServiceUserByEmail(loginRequest.getEmail());
        if(serviceUser.getId() > 0){
            if(serviceUser.getIs_enabled().equals("true")){
                if(Hasher.checkPassword(loginRequest.getPass(), serviceUser.getPass())){
                    return new Token(generateToken(serviceUser, serviceUser.getUser_role()));
                }
            }
        }
        ExceptionMessage exceptionMessage = new ExceptionMessage("LoginException", "Failed to login. There is no user with email: " + loginRequest.getEmail());
        throw new LoginException(exceptionMessage);
    }

    @Override
    public Token loginServiceUserWithToken(String token) throws LoginException, JsonProcessingException, GetException, SQLException, TokenParseException, TokenGenerateException {
        Claims claims = parseToken(token);
        ServiceUser serviceUser = getRawServiceUserByEmail(claims.get("email").toString());
        if(serviceUser.getId() > 0){
            if(serviceUser.getIs_enabled().equals("true")){
                return new Token(generateToken(serviceUser, claims.get("user_role").toString()));
            }
        }
        ExceptionMessage exceptionMessage = new ExceptionMessage("LoginException", "Failed to login user with token: " + token);
        throw new LoginException(exceptionMessage);
    }

    @Override
    public Token logoutServiceUser() throws JsonProcessingException, TokenGenerateException {
        ServiceUser serviceUser = new ServiceUser(0, "", "", "", Util.ROLE_GUEST, "true", "", "");
        return new Token(generateToken(serviceUser, Util.ROLE_GUEST));
    }

    @Override
    public Integer deleteServiceUserById(Integer id) throws JsonProcessingException, DeleteException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "DELETE FROM service_user WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            articleService.deleteAllArticlesByServiceUserId(id);
            return affectedRows;
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("DeleteException", e.getMessage());
            throw new DeleteException(exceptionMessage);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public String generateToken(ServiceUser serviceUser, String userRole) throws JsonProcessingException, TokenGenerateException {
        try{
            Claims claims = Jwts.claims();
            claims.put("id", serviceUser.getId());
            claims.put("username", serviceUser.getUsername());
            claims.put("email", serviceUser.getEmail());
            claims.put("pass", serviceUser.getPass());
            claims.put("user_role", userRole);
            claims.put("is_enabled", serviceUser.getIs_enabled());
            if(Objects.equals(userRole, Util.ROLE_GUEST)){
                return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
            }
            else{
                return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, JWT_SECRET).setExpiration(java.sql.Date.from(LocalDateTime.now().plusDays(7).atZone(ZoneId.systemDefault()).toInstant())).compact();
            }
        }
        catch(Exception e){
            ExceptionMessage exceptionMessage = new ExceptionMessage("TokenGenerateException", "Failed to generate token. Reason: " + e.getMessage());
            throw new TokenGenerateException(exceptionMessage);
        }
    }
    @Override
    public Claims parseToken(String token) throws JsonProcessingException, TokenParseException {
        try {
            return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        }
        catch(Exception e){
            ExceptionMessage exceptionMessage = new ExceptionMessage("TokenParseException", "Failed to parse token. Reason: " + e.getMessage());
            throw new TokenParseException(exceptionMessage);
        }
    }
    private ServiceUser extractServiceUserFromResultSet(ResultSet resultSet) throws SQLException {
        Integer columnId = resultSet.getInt("id");
        String columnUsername = resultSet.getString("username");
        String columnEmail = resultSet.getString("email");
        String columnPass = resultSet.getString("pass");
        String columnUserRole = resultSet.getString("user_role");
        String columnIsEnabled = resultSet.getString("is_enabled");
        String columnFirstName = resultSet.getString("first_name");
        String columnLastName = resultSet.getString("last_name");
        return new ServiceUser(columnId, columnUsername, columnEmail, columnPass, columnUserRole, columnIsEnabled, columnFirstName, columnLastName);
    }
    private Integer saveServiceUser(ServiceUser serviceUser) throws JsonProcessingException, UpdateException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "UPDATE service_user SET username = ?, email = ?, pass = ?, user_role = ?, is_enabled = ?, first_name = ?, last_name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, serviceUser.getUsername());
            preparedStatement.setString(2, serviceUser.getEmail());
            preparedStatement.setString(3, serviceUser.getPass());
            preparedStatement.setString(4, serviceUser.getUser_role());
            preparedStatement.setString(5, serviceUser.getIs_enabled());
            preparedStatement.setString(6, serviceUser.getFirst_name());
            preparedStatement.setString(7, serviceUser.getLast_name());
            preparedStatement.setInt(8, serviceUser.getId());
            return preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("UpdateException", "Failed to update user with id: " + serviceUser.getId());
            throw new UpdateException(exceptionMessage);
        }
        finally {
            connection.close();
        }
    }
}
