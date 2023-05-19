package rs.raf.rafnews.service.implementation;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.ServiceUserDto;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.ServiceUser;
import rs.raf.rafnews.model.ServiceUserLogin;
import rs.raf.rafnews.model.ServiceUserRegister;
import rs.raf.rafnews.model.Token;
import rs.raf.rafnews.repository.specification.IServiceUserRepository;
import rs.raf.rafnews.service.specification.IServiceUserService;

import java.util.List;
@RequestScoped
public class ServiceUserService implements IServiceUserService {
    @Inject
    private IServiceUserRepository serviceUserRepository;

    public ServiceUserService(){

    }
    @Override
    public List<ServiceUser> getAllRawServiceUsers() throws GetException, ResourceNotFoundException, JsonProcessingException {
        return this.serviceUserRepository.getAllRawServiceUsers();
    }
    @Override
    public List<ServiceUserDto> getAllServiceUsers() throws GetException, ResourceNotFoundException, JsonProcessingException {
        return this.serviceUserRepository.getAllServiceUsers();
    }

    @Override
    public ServiceUserDto joinServiceUser(ServiceUser serviceUser) {
        return this.serviceUserRepository.joinServiceUser(serviceUser);
    }

    @Override
    public ServiceUser getRawServiceUserById(Integer id) throws JsonProcessingException, GetException {
        return this.serviceUserRepository.getRawServiceUserById(id);
    }
    @Override
    public ServiceUserDto getServiceUserById(Integer id) throws JsonProcessingException, GetException {
        return this.serviceUserRepository.getServiceUserById(id);
    }
    @Override
    public ServiceUser getRawServiceUserByEmail(String email) throws JsonProcessingException, GetException {
        return this.serviceUserRepository.getRawServiceUserByEmail(email);
    }
    @Override
    public ServiceUserDto getServiceUserByEmail(String email) throws JsonProcessingException, GetException {
        return this.serviceUserRepository.getServiceUserByEmail(email);
    }

    @Override
    public ServiceUserDto addServiceUser(ServiceUser serviceUser) throws AddException, JsonProcessingException {
        return this.serviceUserRepository.addServiceUser(serviceUser);
    }

    @Override
    public Integer updateServiceUserById(Integer id, ServiceUser serviceUser) throws UpdateException, JsonProcessingException, GetException {
        return this.serviceUserRepository.updateServiceUserById(id, serviceUser);
    }

    @Override
    public ServiceUserDto registerServiceUser(ServiceUserRegister serviceUserRegister) throws RegisterException, JsonProcessingException, EmailAlreadyExists, GetException {
        return this.serviceUserRepository.registerServiceUser(serviceUserRegister);
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
    public Integer deleteServiceUserById(Integer id) throws DeleteException, JsonProcessingException {
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