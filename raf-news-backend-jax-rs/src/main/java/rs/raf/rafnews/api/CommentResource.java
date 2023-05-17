package rs.raf.rafnews.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import rs.raf.rafnews.model.*;
import rs.raf.rafnews.service.implementation.CommentService;
import rs.raf.rafnews.service.implementation.ServiceUserService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/comment")
public class CommentResource {
    @Inject
    CommentService commentService;

    @GET
    @Path("/get/{id}")
    @Produces(APPLICATION_JSON)
    public Response getCommentById(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            Comment comment = commentService.getCommentById(id);
            return Response.ok().entity(comment).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @GET
    @Path("/getAll")
    @Produces(APPLICATION_JSON)
    public Response getAllComments(@HeaderParam("Authorization") String bearerToken){
        try{
            List<Comment> commentList = commentService.getAllComments();
            return Response.ok().entity(commentList).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @Path("/add")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response addComment(Comment comment, @HeaderParam("Authorization") String bearerToken){
        try{
            Comment commentWithId = commentService.addComment(comment);
            return Response.ok().entity(commentWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }
    @POST
    @Path("/update")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response updateComment(Comment comment, @HeaderParam("Authorization") String bearerToken){
        try{
            Comment commentWithId = commentService.addComment(comment);
            return Response.ok().entity(commentWithId).build();
        }
        catch(Exception e){
            return Response.status(500).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(APPLICATION_JSON)
    public Response deleteComment(@PathParam("id") Integer id, @HeaderParam("Authorization") String bearerToken){
        try{
            boolean isDeleted = commentService.deleteCommentById(id);
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
