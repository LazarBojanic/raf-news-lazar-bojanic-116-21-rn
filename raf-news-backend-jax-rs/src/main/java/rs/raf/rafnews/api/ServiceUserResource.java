package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import rs.raf.rafnews.model.ServiceUser;
import rs.raf.rafnews.model.ServiceUserLogin;
import rs.raf.rafnews.model.ServiceUserRegister;
import rs.raf.rafnews.model.Token;
import rs.raf.rafnews.service.implementation.ServiceUserService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/service_user")
public class ServiceUserResource {
    @Inject
    ServiceUserService serviceUserService;

    @GET
    @Path("/get/{id}")
    @Produces(APPLICATION_JSON)
    public Response getServiceUserById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            ServiceUser serviceUser = serviceUserService.getServiceUserById(id);
            return Response.ok().entity(serviceUser).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllServiceUsers(@HeaderParam("Authorization") String bearerToken){
        try{
            List<ServiceUser> serviceUserList = serviceUserService.getAllServiceUsers();
            return Response.ok().entity(serviceUserList).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @Path("/add")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addServiceUser(ServiceUser serviceUser, @HeaderParam("Authorization") String bearerToken){
        try{
            ServiceUser serviceUserWithId = serviceUserService.addServiceUser(serviceUser);
            return Response.ok().entity(serviceUserWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }

    @POST
    @Path("/register")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response registerServiceUser(ServiceUserRegister serviceUserRegister){
        try{
            ServiceUser serviceUserWithId = serviceUserService.registerServiceUser(serviceUserRegister);
            return Response.ok().entity(serviceUserWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response loginServiceUser(ServiceUserLogin serviceUserLogin){
        try{
            Token token = serviceUserService.loginServiceUser(serviceUserLogin);
            return Response.ok().entity(token).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteServiceUser(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            boolean isDeleted = serviceUserService.deleteServiceUserById(id);
            if(isDeleted){
                return Response.ok().entity(id).build();
            }
            else{
                return Response.status(500).build();
            }
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
}
