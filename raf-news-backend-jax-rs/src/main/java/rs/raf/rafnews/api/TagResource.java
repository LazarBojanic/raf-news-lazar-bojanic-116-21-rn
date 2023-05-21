package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import rs.raf.rafnews.dto.TagDto;
import rs.raf.rafnews.logging.LogEndpoint;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.service.implementation.TagService;
import rs.raf.rafnews.service.implementation.ServiceUserService;
import rs.raf.rafnews.service.specification.ITagService;

import java.sql.Timestamp;
import java.time.Instant;
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
            TagDto tagDto = tagService.getTagById(id);
            return Response.ok().entity(tagDto).build();
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
            List<TagDto> tagDtoList = tagService.getAllTags();
            return Response.ok().entity(tagDtoList).build();
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
            TagDto tagDtoWithId = tagService.addTag(tag);
            return Response.ok().entity(tagDtoWithId).build();
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
    @Path("/delete/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteTag(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
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
