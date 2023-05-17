package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import rs.raf.rafnews.dto.ServiceUserDto;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.ServiceUser;
import rs.raf.rafnews.model.ServiceUserLogin;
import rs.raf.rafnews.model.ServiceUserRegister;
import rs.raf.rafnews.model.Token;
import rs.raf.rafnews.service.specification.IServiceUserService;
import rs.raf.rafnews.exception.ExceptionMessage;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/service_user")
public class ServiceUserResource {
    @Inject
    IServiceUserService serviceUserService;

    @GET
    @Path("/getAllRaw")
    @Produces(APPLICATION_JSON)
    public Response getAllRawServiceUsers(@HeaderParam("Authorization") String bearerToken){
        try{
            List<ServiceUser> serviceUserList = serviceUserService.getAllRawServiceUsers();
            return Response.ok().entity(serviceUserList).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllServiceUsers(@HeaderParam("Authorization") String bearerToken){
        try{
            List<ServiceUserDto> serviceUserDtoList = serviceUserService.getAllServiceUsers();
            return Response.ok().entity(serviceUserDtoList).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getRawById/{id}")
    @Produces(APPLICATION_JSON)
    public Response getRawServiceUserById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            ServiceUser serviceUser = serviceUserService.getRawServiceUserById(id);
            return Response.ok().entity(serviceUser).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/getById/{id}")
    @Produces(APPLICATION_JSON)
    public Response getServiceUserById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            ServiceUserDto serviceUserDto = serviceUserService.getServiceUserById(id);
            return Response.ok().entity(serviceUserDto).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getRawByEmail/{email}")
    @Produces(APPLICATION_JSON)
    public Response getRawServiceUserById(@PathParam("email") String email, @HeaderParam("Authorization") String bearerToken){
        try{
            ServiceUser serviceUser = serviceUserService.getRawServiceUserByEmail(email);
            return Response.ok().entity(serviceUser).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/getByEmail/{email}")
    @Produces(APPLICATION_JSON)
    public Response getServiceUserById(@PathParam("email") String email, @HeaderParam("Authorization") String bearerToken){
        try{
            ServiceUserDto serviceUserDto = serviceUserService.getServiceUserByEmail(email);
            return Response.ok().entity(serviceUserDto).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/add")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addServiceUser(ServiceUser serviceUser, @HeaderParam("Authorization") String bearerToken){
        try{
            ServiceUserDto serviceUserDtoWithId = serviceUserService.addServiceUser(serviceUser);
            return Response.ok().entity(serviceUserDtoWithId).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @POST
    @Path("/update/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateServiceUser(@PathParam("id") Integer id, ServiceUser serviceUser,  @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = serviceUserService.updateServiceUser(id, serviceUser);
            return Response.ok().entity(affectedRows).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/register")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response registerServiceUser(ServiceUserRegister serviceUserRegister){
        try{
            ServiceUserDto serviceUserDtoWithId = serviceUserService.registerServiceUser(serviceUserRegister);
            return Response.ok().entity(serviceUserDtoWithId).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
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
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/loginWithToken")
    @Produces(APPLICATION_JSON)
    public Response loginServiceUserByToken(@HeaderParam("Authorization") String bearerToken){
        try{
            String token = "";
            if(bearerToken.startsWith("Bearer ")){
                token = bearerToken.split(" ")[1];
            }
            Token newToken = serviceUserService.loginServiceUserWithToken(token);
            return Response.ok().entity(newToken).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/logout")
    @Produces(APPLICATION_JSON)
    public Response logoutServiceUser(){
        try{
            Token token = serviceUserService.logoutServiceUser();
            return Response.ok().entity(token).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteServiceUser(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            Integer rowsAffected = serviceUserService.deleteServiceUserById(id);
            return Response.ok().entity(rowsAffected).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
