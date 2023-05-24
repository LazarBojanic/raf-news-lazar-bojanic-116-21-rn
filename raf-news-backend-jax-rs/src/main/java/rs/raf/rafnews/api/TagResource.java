package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import rs.raf.rafnews.logging.LogEndpoint;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.service.specification.ITagService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/tag")
public class TagResource {
    @Inject
    ITagService tagService;

    @LogEndpoint
    @GET
    @Path("/get/{id}")
    @Produces(APPLICATION_JSON)
    public Response getTagById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            Tag tag = tagService.getTagById(id);
            return Response.ok().entity(tag).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @LogEndpoint
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllTags(@HeaderParam("Authorization") String bearerToken){
        try{
            List<Tag> tagList = tagService.getAllTags();
            return Response.ok().entity(tagList).build();
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
    public Response addTag(Tag tag, @HeaderParam("Authorization") String bearerToken){
        try{
            Tag tagWithId = tagService.addTag(tag);
            return Response.ok().entity(tagWithId).build();
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
    public Response updateTagById(@PathParam("id") Integer id, Tag tag, @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = tagService.updateTagById(id, tag);
            return Response.ok().entity(affectedRows).build();
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
    public Response deleteTagById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = tagService.deleteTagById(id);
            return Response.ok().entity(affectedRows).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
