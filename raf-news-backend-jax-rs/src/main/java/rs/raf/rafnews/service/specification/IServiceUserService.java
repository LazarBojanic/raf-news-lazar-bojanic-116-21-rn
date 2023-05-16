package rs.raf.rafnews.service.specification;


import jakarta.inject.Inject;
import rs.raf.rafnews.model.ServiceUser;
import rs.raf.rafnews.model.Token;
import rs.raf.rafnews.repository.specification.IServiceUserRepository;

import java.util.List;

public interface IServiceUserService {

    public List<ServiceUser> getAllServiceUsers();
    public ServiceUser getServiceUserById(Integer serviceUserId);
    public ServiceUser getServiceUserByUsername(String username);
    public ServiceUser addServiceUser(ServiceUser serviceUser);
    public ServiceUser registerServiceUser(ServiceUser serviceUser);
    public Token loginServiceUser(ServiceUser serviceUser);
    public boolean deleteServiceUserById(Integer id);
    public String generateToken(ServiceUser serviceUser);

    public ServiceUser parseToken(String jwt);
}
