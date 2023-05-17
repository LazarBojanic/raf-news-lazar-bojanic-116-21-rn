package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.service.implementation.TagService;
import rs.raf.rafnews.service.implementation.ServiceUserService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/tag")
public class TagResource {
    @Inject
    TagService tagService;

    @GET
    @Path("/get/{id}")
    @Produces(APPLICATION_JSON)
    public Response getTagById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            Tag tag = tagService.getTagById(id);
            return Response.ok().entity(tag).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllTags(@HeaderParam("Authorization") String bearerToken){
        try{
            List<Tag> tagList = tagService.getAllTags();
            return Response.ok().entity(tagList).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @Path("/add")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addTag(Tag tag, @HeaderParam("Authorization") String bearerToken){
        try{
            Tag tagWithId = tagService.addTag(tag);
            return Response.ok().entity(tagWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @Path("/update")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateTag(Tag tag, @HeaderParam("Authorization") String bearerToken){
        try{
            Tag tagWithId = tagService.addTag(tag);
            return Response.ok().entity(tagWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteTag(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            boolean isDeleted = tagService.deleteTagById(id);
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
