package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.ServiceUserDto;
import rs.raf.rafnews.logging.LogEndpoint;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.service.specification.IServiceUserService;
import rs.raf.rafnews.exception.ExceptionMessage;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/service_user")
public class ServiceUserResource {
    @Inject
    IServiceUserService serviceUserService;

    @LogEndpoint
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
    @LogEndpoint
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

    @LogEndpoint
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
    @LogEndpoint
    @PUT
    @Path("/updateById/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateServiceUserById(@PathParam("id") Integer id, ServiceUser serviceUser,  @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = serviceUserService.updateServiceUserById(id, serviceUser);
            return Response.ok().entity(affectedRows).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @LogEndpoint
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

    @LogEndpoint
    @POST
    @Path("/registerFromAdmin")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response registerServiceUserFromAdmin(ServiceUserFromAdminRegister serviceUserFromAdminRegister){
        try{
            ServiceUserDto serviceUserDtoWithId = serviceUserService.registerServiceUserFromAdmin(serviceUserFromAdminRegister);
            return Response.ok().entity(serviceUserDtoWithId).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @LogEndpoint
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
    @LogEndpoint
    @GET
    @Path("/loginWithToken")
    @Produces(APPLICATION_JSON)
    public Response loginServiceUserWithToken(@HeaderParam("Authorization") String bearerToken){
        try{
            if(bearerToken != null){
                String token = "";
                if(bearerToken.startsWith("Bearer ")){
                    token = bearerToken.split(" ")[1];
                }
                Token newToken = serviceUserService.loginServiceUserWithToken(token);
                return Response.ok().entity(newToken).build();
            }
            else{
                return Response.status(500).entity(new ExceptionMessage("LoginException", "Failed to login. Undefined token.")).build();
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @LogEndpoint
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

    @LogEndpoint
    @DELETE
    @Path("/deleteById/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteServiceUserById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
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
