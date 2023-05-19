package rs.raf.rafnews.service.specification;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import rs.raf.rafnews.dto.ServiceUserDto;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.ServiceUser;
import rs.raf.rafnews.model.ServiceUserLogin;
import rs.raf.rafnews.model.ServiceUserRegister;
import rs.raf.rafnews.model.Token;

import java.util.List;

public interface IServiceUserService {
    List<ServiceUser> getAllRawServiceUsers() throws JsonProcessingException, ResourceNotFoundException, GetException;
    List<ServiceUserDto> getAllServiceUsers() throws ResourceNotFoundException, JsonProcessingException, GetException;
    ServiceUserDto joinServiceUser(ServiceUser serviceUser);
    ServiceUser getRawServiceUserById(Integer id) throws JsonProcessingException, GetException;
    ServiceUserDto getServiceUserById(Integer id) throws JsonProcessingException, GetException;
    ServiceUser getRawServiceUserByEmail(String email) throws JsonProcessingException, GetException;
    ServiceUserDto getServiceUserByEmail(String email) throws JsonProcessingException, GetException;
    ServiceUserDto addServiceUser(ServiceUser serviceUser) throws JsonProcessingException, AddException;
    Integer updateServiceUserById(Integer id, ServiceUser serviceUser) throws JsonProcessingException, UpdateException, GetException;
    ServiceUserDto registerServiceUser(ServiceUserRegister serviceUserRegister) throws JsonProcessingException, RegisterException, EmailAlreadyExists, GetException;
    Token loginServiceUser(ServiceUserLogin serviceUserLogin) throws LoginException, JsonProcessingException, GetException;
    Token loginServiceUserWithToken(String token) throws LoginException, JsonProcessingException, GetException;
    Token logoutServiceUser() throws JsonProcessingException, LogoutException;
    Integer deleteServiceUserById(Integer id) throws JsonProcessingException, DeleteException;
    String generateToken(ServiceUser serviceUser, String userRole) throws JsonProcessingException, TokenGenerateException;
    Claims parseToken(String token) throws LoginException, JsonProcessingException, TokenParseException;
}
