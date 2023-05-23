package rs.raf.rafnews.repository.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import rs.raf.rafnews.dto.ServiceUserDto;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.*;

import java.sql.SQLException;
import java.util.List;

public interface IServiceUserRepository {
    List<ServiceUser> getAllRawServiceUsers() throws JsonProcessingException, ResourceNotFoundException, GetException, SQLException;
    List<ServiceUserDto> getAllServiceUsers() throws ResourceNotFoundException, JsonProcessingException, GetException, SQLException;
    ServiceUserDto joinServiceUser(ServiceUser serviceUser);
    ServiceUser getRawServiceUserById(Integer id) throws JsonProcessingException, GetException, SQLException;
    ServiceUserDto getServiceUserById(Integer id) throws JsonProcessingException, GetException, SQLException;
    ServiceUser getRawServiceUserByEmail(String email) throws JsonProcessingException, GetException, SQLException;
    ServiceUser getRawServiceUserByUsername(String username) throws JsonProcessingException, GetException, SQLException;
    ServiceUser getRawServiceUserByEmailOrUsername(String email, String username) throws JsonProcessingException, GetException, SQLException;
    ServiceUserDto getServiceUserByEmail(String email) throws JsonProcessingException, GetException, SQLException;
    ServiceUserDto addServiceUser(ServiceUser serviceUser) throws JsonProcessingException, AddException, SQLException;
    Integer updateServiceUserById(Integer id, ServiceUser serviceUser) throws JsonProcessingException, UpdateException, GetException;
    ServiceUserDto registerServiceUser(ServiceUserRegister serviceUserRegister) throws JsonProcessingException, RegisterException;
    ServiceUserDto registerServiceUserFromAdmin(ServiceUserFromAdminRegister serviceUserFromAdminRegister) throws JsonProcessingException, RegisterException;
    Token loginServiceUser(ServiceUserLogin serviceUserLogin) throws LoginException, JsonProcessingException, GetException;
    Token loginServiceUserWithToken(String token) throws LoginException, JsonProcessingException, GetException;
    Token logoutServiceUser() throws JsonProcessingException, LogoutException;
    Integer deleteServiceUserById(Integer id) throws JsonProcessingException, DeleteException, SQLException;
    String generateToken(ServiceUser serviceUser, String userRole) throws JsonProcessingException, TokenGenerateException;
    Claims parseToken(String token) throws LoginException, JsonProcessingException, TokenParseException;
}
