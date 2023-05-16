package rs.raf.rafnews.repository.implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Singleton;
import rs.raf.rafnews.database.BlogDatabase;
import rs.raf.rafnews.model.ServiceUser;
import rs.raf.rafnews.model.ServiceUserLogin;
import rs.raf.rafnews.model.ServiceUserRegister;
import rs.raf.rafnews.model.Token;
import rs.raf.rafnews.repository.specification.IServiceUserRepository;
import rs.raf.rafnews.util.Hasher;
import rs.raf.rafnews.util.Util;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
@RequestScoped
public class ServiceUserRepository implements IServiceUserRepository {
    private static String jwtSecret = "NQu2mzEtCwrNaJCjsoHT";
    public static String MASTER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsInBhc3MiOiJhZG1pbiJ9.EcbsD0Wn1wkI8iVVTEOX0IWHuwyqOndzPUFtDAM4TMI";
    @Override
    public List<ServiceUser> getAllServiceUsers() {
        List<ServiceUser> serviceUserList = new ArrayList<>();
        String query = "SELECT * FROM service_user";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String pass = resultSet.getString("pass");
                    String user_role = resultSet.getString("user_role");
                    String enabled = resultSet.getString("enabled");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    ServiceUser serviceUser = new ServiceUser(id, email, pass, user_role, enabled, first_name, last_name);
                    serviceUserList.add(serviceUser);
                }
            }
            catch (SQLException e) {
                return null;
            }
        }
        catch (SQLException e) {
            return null;
        }
        return serviceUserList;
    }

    @Override
    public ServiceUser getServiceUserById(Integer serviceUserId) {
        String query = "SELECT * FROM service_user WHERE id = ?";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, serviceUserId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String pass = resultSet.getString("pass");
                    String user_role = resultSet.getString("user_role");
                    String enabled = resultSet.getString("enabled");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    ServiceUser serviceUser = new ServiceUser(id, email, pass, user_role, enabled, first_name, last_name);
                    return serviceUser;
                }
                else{
                    return null;
                }
            }
            catch (SQLException e){
                return null;
            }
        }
        catch (SQLException e) {
            return null;
        }
    }

    @Override
    public ServiceUser getServiceUserByEmail(String serviceUserEmail) {
        String query = "SELECT * FROM service_user WHERE email = ?";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setString(1, serviceUserEmail);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String email = resultSet.getString("email");
                    String pass = resultSet.getString("pass");
                    String user_role = resultSet.getString("user_role");
                    String enabled = resultSet.getString("enabled");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    ServiceUser serviceUser = new ServiceUser(id, email, pass, user_role, enabled, first_name, last_name);
                    return serviceUser;
                }
                else{
                    return null;
                }
            }
            catch (SQLException e){
                return null;
            }
        }
        catch (SQLException e) {
            return null;
        }
    }

    @Override
    public ServiceUser addServiceUser(ServiceUser serviceUser) {
        String query = "INSERT INTO service_user(email, pass, user_role, enabled, first_name, last_name) VALUES(?, ?, ?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, serviceUser.getEmail());
            preparedStatement.setString(2, serviceUser.getPass());
            preparedStatement.setString(3, serviceUser.getUser_role());
            preparedStatement.setString(4, serviceUser.getEnabled());
            preparedStatement.setString(5, serviceUser.getFirst_name());
            preparedStatement.setString(6, serviceUser.getLast_name());
            Integer affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Integer id = generatedKeys.getInt(generatedKeys.findColumn("id"));
                    serviceUser.setId(id);
                    return serviceUser;
                }
                else {
                    return null;
                }
            }
            else{
                return null;
            }
        }
        catch (SQLException e){
            return null;
        }
    }

    @Override
    public ServiceUser registerServiceUser(ServiceUserRegister serviceUserRegister) {
        try{
            if(getServiceUserByEmail(serviceUserRegister.getEmail()) == null){
                String hashedPass = Hasher.hashPassword(serviceUserRegister.getPass());
                ServiceUser serviceUser = new ServiceUser(0, serviceUserRegister.getEmail(), hashedPass, Util.ROLE_CONTENT_CREATOR, "true", serviceUserRegister.getFirst_name(), serviceUserRegister.getLast_name());
                return addServiceUser(serviceUser);
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public Token loginServiceUser(ServiceUserLogin serviceUserLogin) {
        try{
            ServiceUser serviceUser = getServiceUserByEmail(serviceUserLogin.getEmail());
            if(serviceUser != null){
                if(serviceUser.getEnabled().equals("true")){
                    if(Hasher.checkPassword(serviceUserLogin.getPass(), serviceUser.getPass())){
                        return new Token(generateToken(serviceUser, Util.ROLE_CONTENT_CREATOR));
                    }
                    else{
                        return null;
                    }
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public Token logoutServiceUser() {
        ServiceUser serviceUser = new ServiceUser(0, "", "", Util.ROLE_GUEST, "false", "", "");
        return new Token(generateToken(serviceUser, Util.ROLE_GUEST));
    }

    @Override
    public boolean deleteServiceUserById(Integer id) {
        String query = "DELETE FROM service_user WHERE id = ?";
        try (PreparedStatement preparedStatement = BlogDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, id);
            Integer rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e) {
            return false;
        }
    }

    @Override
    public String generateToken(ServiceUser serviceUser, String userRole) {
        Claims claims = Jwts.claims();
        claims.put("id", serviceUser.getId());
        claims.put("email", serviceUser.getEmail());
        claims.put("pass", serviceUser.getPass());
        claims.put("user_role", userRole);
        claims.put("enabled", serviceUser.getEnabled());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setExpiration(java.sql.Date.from(LocalDateTime.now().plusHours(2).atZone(ZoneId.systemDefault()).toInstant()))
                .compact();
    }
    @Override
    public Claims parseToken(String jwt) {
        try {
            Claims claims;
            claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(jwt)
                    .getBody();
            return claims;
        }
        catch (Exception e) {
            return null;
        }
    }
}
