package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import rs.raf.rafnews.dto.CommentDto;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.service.implementation.CommentService;
import rs.raf.rafnews.service.implementation.ServiceUserService;
import rs.raf.rafnews.service.specification.ICommentService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/comment")
public class CommentResource {
    @Inject
    ICommentService commentService;

    @GET
    @Path("/getById/{id}")
    @Produces(APPLICATION_JSON)
    public Response getCommentById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            CommentDto commentDto = commentService.getCommentById(id);
            return Response.ok().entity(commentDto).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllComments(@HeaderParam("Authorization") String bearerToken){
        try{
            List<CommentDto> commentDtoList = commentService.getAllComments();
            return Response.ok().entity(commentDtoList).build();
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
    public Response addComment(Comment comment, @HeaderParam("Authorization") String bearerToken){
        try{
            CommentDto commentDtoWithId = commentService.addComment(comment);
            return Response.ok().entity(commentDtoWithId).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
    @PUT
    @Path("/updateById/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateCommentById(@PathParam("id") Integer id, Comment comment, @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = commentService.updateCommentById(id, comment);
            return Response.ok().entity(affectedRows).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deleteById/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteCommentById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            Integer affectedRows = commentService.deleteCommentById(id);
            return Response.ok().entity(affectedRows).build();
        }
        catch(Exception e){
            e.printStackTrace();
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
