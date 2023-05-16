package rs.raf.rafnews.service.implementation;


import io.jsonwebtoken.Claims;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Scope;
import jakarta.inject.Singleton;
import rs.raf.rafnews.model.ServiceUser;
import rs.raf.rafnews.model.ServiceUserLogin;
import rs.raf.rafnews.model.ServiceUserRegister;
import rs.raf.rafnews.model.Token;
import rs.raf.rafnews.repository.specification.IServiceUserRepository;
import rs.raf.rafnews.service.specification.IServiceUserService;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
@RequestScoped
public class ServiceUserService implements IServiceUserService {
    @Inject
    private IServiceUserRepository serviceUserRepository;

    public ServiceUserService(){

    }
    @Override
    public List<ServiceUser> getAllServiceUsers() {
        return this.serviceUserRepository.getAllServiceUsers();
    }

    @Override
    public ServiceUser getServiceUserById(Integer serviceUserId) {
        return this.serviceUserRepository.getServiceUserById(serviceUserId);
    }

    @Override
    public ServiceUser getServiceUserByUsername(String username) {
        return this.serviceUserRepository.getServiceUserByEmail(username);
    }

    @Override
    public ServiceUser addServiceUser(ServiceUser serviceUser) {
        return this.serviceUserRepository.addServiceUser(serviceUser);
    }

    @Override
    public ServiceUser registerServiceUser(ServiceUserRegister serviceUserRegister) {
        return this.serviceUserRepository.registerServiceUser(serviceUserRegister);
    }

    @Override
    public Token loginServiceUser(ServiceUserLogin serviceUserLogin) {
        return this.serviceUserRepository.loginServiceUser(serviceUserLogin);
    }

    @Override
    public Token logoutServiceUser() {
        return this.serviceUserRepository.logoutServiceUser();
    }

    @Override
    public boolean deleteServiceUserById(Integer id) {
        return this.serviceUserRepository.deleteServiceUserById(id);
    }

    @Override
    public String generateToken(ServiceUser serviceUser, String userRole) {
        return this.serviceUserRepository.generateToken(serviceUser, userRole);
    }

    @Override
    public Claims parseToken(String jwt) {
        return this.serviceUserRepository.parseToken(jwt);
    }
}