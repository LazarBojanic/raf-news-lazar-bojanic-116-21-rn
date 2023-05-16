package rs.raf.rafnews.service.specification;


import io.jsonwebtoken.Claims;
import jakarta.inject.Inject;
import rs.raf.rafnews.model.ServiceUser;
import rs.raf.rafnews.model.ServiceUserLogin;
import rs.raf.rafnews.model.ServiceUserRegister;
import rs.raf.rafnews.model.Token;
import rs.raf.rafnews.repository.specification.IServiceUserRepository;

import java.util.List;

public interface IServiceUserService {

    List<ServiceUser> getAllServiceUsers();
    ServiceUser getServiceUserById(Integer serviceUserId);
    ServiceUser getServiceUserByUsername(String username);
    ServiceUser addServiceUser(ServiceUser serviceUser);
    ServiceUser registerServiceUser(ServiceUserRegister serviceUserRegister);
    Token loginServiceUser(ServiceUserLogin serviceUserLogin);
    Token logoutServiceUser();
    boolean deleteServiceUserById(Integer id);
    String generateToken(ServiceUser serviceUser, String userRole);
    Claims parseToken(String jwt);
}
