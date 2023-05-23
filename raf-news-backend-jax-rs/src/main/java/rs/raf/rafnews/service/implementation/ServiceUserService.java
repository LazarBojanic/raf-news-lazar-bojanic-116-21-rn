package rs.raf.rafnews.service.implementation;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.ServiceUserDto;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.repository.specification.IServiceUserRepository;
import rs.raf.rafnews.service.specification.IServiceUserService;

import java.sql.SQLException;
import java.util.List;
@RequestScoped
public class ServiceUserService implements IServiceUserService {
    @Inject
    private IServiceUserRepository serviceUserRepository;

    public ServiceUserService(){

    }
    @Override
    public List<ServiceUser> getAllRawServiceUsers() throws GetException, ResourceNotFoundException, JsonProcessingException, SQLException {
        return this.serviceUserRepository.getAllRawServiceUsers();
    }
    @Override
    public List<ServiceUserDto> getAllServiceUsers() throws GetException, ResourceNotFoundException, JsonProcessingException, SQLException {
        return this.serviceUserRepository.getAllServiceUsers();
    }

    @Override
    public ServiceUserDto joinServiceUser(ServiceUser serviceUser) {
        return this.serviceUserRepository.joinServiceUser(serviceUser);
    }

    @Override
    public ServiceUser getRawServiceUserById(Integer id) throws JsonProcessingException, GetException, SQLException {
        return this.serviceUserRepository.getRawServiceUserById(id);
    }
    @Override
    public ServiceUserDto getServiceUserById(Integer id) throws JsonProcessingException, GetException, SQLException {
        return this.serviceUserRepository.getServiceUserById(id);
    }
    @Override
    public ServiceUser getRawServiceUserByEmail(String email) throws JsonProcessingException, GetException, SQLException {
        return this.serviceUserRepository.getRawServiceUserByEmail(email);
    }
    @Override
    public ServiceUserDto getServiceUserByEmail(String email) throws JsonProcessingException, GetException, SQLException {
        return this.serviceUserRepository.getServiceUserByEmail(email);
    }

    @Override
    public ServiceUser getRawServiceUserByUsername(String username) throws JsonProcessingException, GetException, SQLException {
        return this.serviceUserRepository.getRawServiceUserByUsername(username);
    }

    @Override
    public ServiceUser getRawServiceUserByEmailOrUsername(String email, String username) throws JsonProcessingException, GetException, SQLException {
        return this.serviceUserRepository.getRawServiceUserByEmailOrUsername(email, username);
    }

    @Override
    public ServiceUserDto addServiceUser(ServiceUser serviceUser) throws AddException, JsonProcessingException, SQLException {
        return this.serviceUserRepository.addServiceUser(serviceUser);
    }

    @Override
    public Integer updateServiceUserById(Integer id, ServiceUser serviceUser) throws UpdateException, JsonProcessingException, GetException {
        return this.serviceUserRepository.updateServiceUserById(id, serviceUser);
    }

    @Override
    public ServiceUserDto registerServiceUser(ServiceUserRegister serviceUserRegister) throws RegisterException, JsonProcessingException {
        return this.serviceUserRepository.registerServiceUser(serviceUserRegister);
    }

    @Override
    public ServiceUserDto registerServiceUserFromAdmin(ServiceUserFromAdminRegister serviceUserFromAdminRegister) throws JsonProcessingException, RegisterException{
        return this.serviceUserRepository.registerServiceUserFromAdmin(serviceUserFromAdminRegister);
    }

    @Override
    public Token loginServiceUser(ServiceUserLogin serviceUserLogin) throws LoginException, JsonProcessingException, GetException {
        return this.serviceUserRepository.loginServiceUser(serviceUserLogin);
    }

    @Override
    public Token loginServiceUserWithToken(String token) throws LoginException, JsonProcessingException, GetException {
        return this.serviceUserRepository.loginServiceUserWithToken(token);
    }

    @Override
    public Token logoutServiceUser() throws JsonProcessingException, LogoutException {
        return this.serviceUserRepository.logoutServiceUser();
    }

    @Override
    public Integer deleteServiceUserById(Integer id) throws DeleteException, JsonProcessingException, SQLException {
        return this.serviceUserRepository.deleteServiceUserById(id);
    }

    @Override
    public String generateToken(ServiceUser serviceUser, String userRole) throws TokenGenerateException, JsonProcessingException {
        return this.serviceUserRepository.generateToken(serviceUser, userRole);
    }

    @Override
    public Claims parseToken(String token) throws LoginException, TokenParseException, JsonProcessingException {
        return this.serviceUserRepository.parseToken(token);
    }
}