package rs.raf.rafnews.service.implementation;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import rs.raf.rafnews.model.ServiceUser;
import rs.raf.rafnews.model.ServiceUserLogin;
import rs.raf.rafnews.model.ServiceUserRegister;
import rs.raf.rafnews.model.Token;
import rs.raf.rafnews.repository.specification.IServiceUserRepository;

import java.util.List;
@Singleton
public class ServiceUserService implements IServiceUserRepository{
    public ServiceUserService() {

    }
    @Inject
    private IServiceUserRepository serviceUserRepository;
    @Override
    public List<ServiceUser> getAllServiceUsers() {
        return this.serviceUserRepository.getAllServiceUsers();
    }
    @Override
    public ServiceUser getServiceUserById(Integer serviceUserId) {
        return this.serviceUserRepository.getServiceUserById(serviceUserId);
    }
    @Override
    public ServiceUser getServiceUserByEmail(String username) {
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
    public boolean deleteServiceUserById(Integer id) {
        return this.serviceUserRepository.deleteServiceUserById(id);
    }
    @Override
    public String generateToken(ServiceUser serviceUser){
        return this.serviceUserRepository.generateToken(serviceUser);
    }
    @Override
    public ServiceUser parseToken(String jwt){
        return this.serviceUserRepository.parseToken(jwt);
    }
}