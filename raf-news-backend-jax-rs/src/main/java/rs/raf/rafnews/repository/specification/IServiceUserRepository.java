package rs.raf.rafnews.repository.specification;

import rs.raf.rafnews.model.ServiceUser;
import rs.raf.rafnews.model.ServiceUserLogin;
import rs.raf.rafnews.model.ServiceUserRegister;
import rs.raf.rafnews.model.Token;

import java.util.List;

public interface IServiceUserRepository {
    List<ServiceUser> getAllServiceUsers();
    ServiceUser getServiceUserById(Integer id);
    ServiceUser getServiceUserByEmail(String username);
    ServiceUser addServiceUser(ServiceUser serviceUser);
    ServiceUser registerServiceUser(ServiceUserRegister serviceUserRegister);
    Token loginServiceUser(ServiceUserLogin serviceUserLogin);
    boolean deleteServiceUserById(Integer id);
    String generateToken(ServiceUser serviceUser);
    ServiceUser parseToken(String jwt);
}
